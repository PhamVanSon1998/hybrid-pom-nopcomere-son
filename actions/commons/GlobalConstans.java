package commons;

public class GlobalConstans {
	public static final String DEV_SERVER = "";
	public static final String TESTING_SERVER = "";
	public static final String STAGING_SERVER = "";
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;

	private static final String OSNAME = System.getProperty("os.name");
	public static final String ROOT_FOLDER = System.getProperty("user.dir");
	public static final String BROWSER_LOG_FOLDER = ROOT_FOLDER + getDirectorySlash("browserLog");
	public static final String DOWLOAD_FOLDER = ROOT_FOLDER + getDirectorySlash("dowloadFiles");
	public static final String UPLOAD_FOLDER = ROOT_FOLDER + getDirectorySlash("uploadFiles");
	public static final String AUTOIT_FOLDER = ROOT_FOLDER + getDirectorySlash("autoITScript");
	

	private static String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}

	private static boolean isWindows() {
		return (GlobalConstans.OSNAME.toLowerCase().indexOf("win") >= 0);
	}

	private static boolean isMac() {
		return (GlobalConstans.OSNAME.toLowerCase().indexOf("mac") >= 0);
	}

	private static boolean isUnix() {
		return (GlobalConstans.OSNAME.toLowerCase().indexOf("nix") >= 0 || GlobalConstans.OSNAME.toLowerCase().indexOf("nux") >= 0);
	}

	private static boolean isSolaris() {
		return (GlobalConstans.OSNAME.toLowerCase().indexOf("sunos") >= 0);
	}
}

