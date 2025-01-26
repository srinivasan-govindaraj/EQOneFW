############################################################
One Single Command
sudo ANDROID_HOME=$HOME/Library/Android/sdk ANDROID_SDK_ROOT=$HOME/Library/Android/sdk appium --allow-insecure chromedriver_autodownload,safari --base-path /wd/hub
############################################################

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



##IOS

### Please ensure install xcode and set path

## to start the device go to the xocde --> preference --> Simulators --> Select the device and run

## Check the device connectivity
xcrun simctl list

##optional set path
sudo xcode-select --switch /Applications/Xcode.app

## verify path
xcode-select -p

## Accept the licence
sudo xcodebuild -license accept

## installation command tools
xcode-select --install


## if command not recognized issue use this option :
Open Xcode and go to Xcode - Preferences - Locations. Ensure that the Command Line Tools dropdown is set to the installed Xcode version



Create a file named .appiumrc.json in the root directory of your project.
#Add your Appium configuration to the file using JSON format. Here's an example:
# json config file
{
  "server": {
    "address": "0.0.0.0",
    "port": 4723,
    "allow-insecure": ["chromedriver_autodownload", "safari"],
    "base-path": "/wd/hub",
    "log": "./appium.log",
    "log-level": "info",
    "relaxed-security": true,
    "use-drivers": ["xcuitest", "uiautomator2"],
    "use-plugins": ["images", "execute-driver"],
    "driver": {
      "xcuitest": {
        "webkit-debug-proxy-port": 27753
      },
      "uiautomator2": {
        "system-port": 8200
      }
    }
  }
}

appium --config-file /path/to/your/.appiumrc.json


