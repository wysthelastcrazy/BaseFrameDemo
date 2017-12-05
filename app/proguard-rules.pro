# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontpreverify
	-optimizationpasses 7
 	-dontusemixedcaseclassnames
	-dontskipnonpubliclibraryclasses
	-dontpreverify
	-verbose
	-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

	-keep public class * extends android.app.Activity
	-keep public class * extends android.app.Application
	-keep public class * extends android.app.Service
	-keep public class * extends android.content.BroadcastReceiver
	-keep public class * extends android.content.ContentProvider
	-keep public class * extends android.app.backup.BackupAgentHelper
	-keep public class * extends android.preference.Preference
	-keep public class com.android.vending.licensing.ILicensingService

	-keep class * implements java.io.Serializable
	-keep class * implements android.os.Parcelable {public static final android.os.Parcelable$Creator *;}
	-keepclassmembers enum * {public static **[] values(); public static ** valueOf(java.lang.String);}

	#所有的属性都不混淆
	-keep public class * implements java.io.Serializable {
    	public *;
	}
	-keepclassmembers class * implements java.io.Serializable {
	    static final long serialVersionUID;
	    private static final java.io.ObjectStreamField[] serialPersistentFields;
	    private void writeObject(java.io.ObjectOutputStream);
	    private void readObject(java.io.ObjectInputStream);
	    java.lang.Object writeReplace();
	    java.lang.Object readResolve();
	}

	-keepclasseswithmembernames class * {
	    native <methods>;
	}

	-keep public class * extends android.view.View {
	    public <init>(android.content.Context);
	    public <init>(android.content.Context, android.util.AttributeSet);
	    public <init>(android.content.Context, android.util.AttributeSet, int);
	    public void set*(...);
	    public void get*(...);
	}

	-keepclasseswithmembernames class * {
	    public <init>(android.content.Context, android.util.AttributeSet);
	}

	-keepclasseswithmembernames class * {
	    public <init>(android.content.Context, android.util.AttributeSet, int);
	}

	-keep public class * implements com.kezhanw.component.base.NoConfusion
	-keep public class * implements com.kezhanw.component.base.NoConfusion {*;}


	-keep public class * implements com.loan.listener.LoanNoConfusion
	-keep public class * implements com.loan.listener.LoanNoConfusion {*;}

	-keepattributes *Annotation*




	-dontwarn android.net.http.SslError
	-keep public class android.net.http.SslError{
    	 *;
	}
	-keep public class android.webkit.WebViewClient{
    	*;
	}
	-keep public class android.webkit.WebChromeClient{
    	*;
	}
	-keep public interface android.webkit.WebChromeClient$CustomViewCallback {
    	*;
	}
	-keep public interface android.webkit.ValueCallback {
    	*;
	}
	-keep class * implements android.webkit.WebChromeClient {
    	*;
	}

	-keepclassmembers class * {
		public <methods>;
	}


	#gson相关
	-keep class com.google.**{*;}
	-keep class sun.misc.Unsafe { *; }
	-keepattributes *Annotation*

	-keepattributes Signature
	-keep class com.google.gson.stream.** { *; }

    #--------------------------------------------------------------------------

	#glide相关 不混淆
	-keep class com.bumptech.glide.**{*;}


    -dontwarn okio.**
    -dontwarn retrofit.**
    -keep class retrofit.** { *; }
    -keepclassmembers,allowobfuscation interface * {
        @retrofit.http.** <methods>;
    }

    -dontwarn com.squareup.okhttp.**
