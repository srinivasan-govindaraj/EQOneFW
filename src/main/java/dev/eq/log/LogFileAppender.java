package dev.eq.log;


import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.FileManager;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.net.Advertiser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Plugin(
        name = "LogFile",
        category = "Core",
        elementType = "appender",
        printObject = true
)
public final class LogFileAppender extends AbstractOutputStreamAppender<FileManager> {
    public static final String PLUGIN_NAME = "LogFile";
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private final String fileName;
    private final Advertiser advertiser;
    private final Object advertisement;

    @PluginBuilderFactory
    public static <B extends Builder<B>> B newBuilder() {
        return (B) (new Builder()).asBuilder();
    }

    private LogFileAppender(final String name, final Layout<? extends Serializable> layout, final Filter filter, final FileManager manager, final String filename, final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser, final Property[] properties) {
        super(name, layout, filter, ignoreExceptions, immediateFlush, properties, manager);
        if (advertiser != null) {
            Map<String, String> configuration = new HashMap(layout.getContentFormat());
            configuration.putAll(manager.getContentFormat());
            configuration.put("contentType", layout.getContentType());
            configuration.put("name", name);
            this.advertisement = advertiser.advertise(configuration);
        } else {
            this.advertisement = null;
        }

        this.fileName = filename;
        this.advertiser = advertiser;
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        this.setStopping();
        super.stop(timeout, timeUnit, false);
        if (this.advertiser != null) {
            this.advertiser.unadvertise(this.advertisement);
        }

        this.setStopped();
        return true;
    }

    private static String prependLocationToFileName(String file) {
        String location = System.getProperty("user.dir") + "/test-output";
        return location + "/" + file;
    }

    public static class Builder<B extends Builder<B>> extends AbstractOutputStreamAppender.Builder<B> implements org.apache.logging.log4j.core.util.Builder<LogFileAppender> {
        @PluginBuilderAttribute
        @Required
        private String fileName;
        @PluginBuilderAttribute
        private boolean append = true;
        @PluginBuilderAttribute
        private boolean locking;
        @PluginBuilderAttribute
        private boolean advertise;
        @PluginBuilderAttribute
        private String advertiseUri;
        @PluginBuilderAttribute
        private boolean createOnDemand;
        @PluginBuilderAttribute
        private String filePermissions;
        @PluginBuilderAttribute
        private String fileOwner;
        @PluginBuilderAttribute
        private String fileGroup;

        public Builder() {
        }

        public LogFileAppender build() {
            boolean bufferedIo = this.isBufferedIo();
            int bufferSize = this.getBufferSize();
            fileName = prependLocationToFileName(fileName);
            if (this.locking && bufferedIo) {
                LogFileAppender.LOGGER.warn("Locking and buffering are mutually exclusive. No buffering will occur for {}", this.fileName);
                bufferedIo = false;
            }

            if (!bufferedIo && bufferSize > 0) {
                LogFileAppender.LOGGER.warn("The bufferSize is set to {} but bufferedIo is false: {}", bufferSize, bufferedIo);
            }

            Layout<? extends Serializable> layout = this.getOrCreateLayout();
            FileManager manager = FileManager.getFileManager(this.fileName, this.append, this.locking, bufferedIo, this.createOnDemand, this.advertiseUri, layout, bufferSize, this.filePermissions, this.fileOwner, this.fileGroup, this.getConfiguration());
            return manager == null ? null : new LogFileAppender(this.getName(), layout, this.getFilter(), manager, this.fileName, this.isIgnoreExceptions(), !bufferedIo || this.isImmediateFlush(), this.advertise ? this.getConfiguration().getAdvertiser() : null, this.getPropertyArray());
        }

        public String getAdvertiseUri() {
            return this.advertiseUri;
        }

        public String getFileName() {
            return this.fileName;
        }

        public boolean isAdvertise() {
            return this.advertise;
        }

        public boolean isAppend() {
            return this.append;
        }

        public boolean isCreateOnDemand() {
            return this.createOnDemand;
        }

        public boolean isLocking() {
            return this.locking;
        }

        public String getFilePermissions() {
            return this.filePermissions;
        }

        public String getFileOwner() {
            return this.fileOwner;
        }

        public String getFileGroup() {
            return this.fileGroup;
        }

        public B withAdvertise(final boolean advertise) {
            this.advertise = advertise;
            return this.asBuilder();
        }

        public B withAdvertiseUri(final String advertiseUri) {
            this.advertiseUri = advertiseUri;
            return this.asBuilder();
        }

        public B withAppend(final boolean append) {
            this.append = append;
            return this.asBuilder();
        }

        public B withFileName(final String fileName) {
            this.fileName = fileName;
            return this.asBuilder();
        }

        public B withCreateOnDemand(final boolean createOnDemand) {
            this.createOnDemand = createOnDemand;
            return this.asBuilder();
        }

        public B withLocking(final boolean locking) {
            this.locking = locking;
            return this.asBuilder();
        }

        public B withFilePermissions(final String filePermissions) {
            this.filePermissions = filePermissions;
            return this.asBuilder();
        }

        public B withFileOwner(final String fileOwner) {
            this.fileOwner = fileOwner;
            return this.asBuilder();
        }

        public B withFileGroup(final String fileGroup) {
            this.fileGroup = fileGroup;
            return this.asBuilder();
        }
    }
}

    

