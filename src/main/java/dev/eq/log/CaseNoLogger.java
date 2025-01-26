package dev.eq.log;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

@Plugin(name = "CaseNoLogger", category = PatternConverter.CATEGORY)
@ConverterKeys({"y", "caseNo"})
public class CaseNoLogger extends LogEventPatternConverter {

    public static CaseNoLogger newInstance(final String[] options) {
        return new CaseNoLogger("caseNo", "caseNo");
    }

    protected CaseNoLogger(String name, String style) {
        super(name, style);
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {

    }

}
