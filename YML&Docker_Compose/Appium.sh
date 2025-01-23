#Software Need to be installed
#For MAC please use brew commands  default nodejs is first one
##Java
brew install openjdk
#Set the java Path-->
sudo ln -sfn $(brew --prefix)/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
#install maven-->
brew install maven
#install npm -->
npm install -g npm@latest
#Check if any issue in Appium -->
appium-doctor -h
#Appium installation is -->
brew install appium
#check the available  driver list -->
appium driver list
# driver -->
sudo appium driver install espresso
sudo appium driver install mac2 ,
# TO check list of driver available -->
appium driver list
# To check available device adb devices --> please ensure setup path properly
############
export ANDROID_HOME=$HOME/Library/Android/sdk
export ANDROID_SDK_ROOT=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools
##############

### Android ####

## Please use android studio to start the emulated device first
## Execute below command
sudo ANDROID_HOME=$HOME/Library/Android/sdk ANDROID_SDK_ROOT=$HOME/Library/Android/sdk appium --allow-insecure chromedriver_autodownload --base-path /wd/hub
####

