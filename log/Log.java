package com.paic.mobilecash.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.util.Log;

public class Log {

	public static final String TAG = "APPLICATION";
	private static final String FILE_NAME = "data/data/packagename/log.txt";
	private static final int MaxBufferSize = 8 * 1024;
	private static final boolean DEBUG = true;

	// Debug Info
	public static void d(String sMessage) {
		if (DEBUG) {
			d(TAG, sMessage);
		}
	}

	public static void d(String sTag, String sMessage) {
		if (DEBUG) {
			if (null != sMessage) {
				Log.d(sTag, sMessage);
			}
		}
	}

	// Warning Info
	public static void w(String sTag, String sMessage) {
		if (DEBUG) {
			if (null != sMessage) {
				Log.w(sTag, sMessage);
			}
		}
	}

	// Error Info
	public static void e(String sMessage) {
		if (DEBUG) {
			if (null != sMessage) {
				e(TAG, sMessage);
			}
		}
	}

	public static void e(String sTag, String sMessage) {
		if (DEBUG) {
			if (null != sMessage) {
				Log.e(sTag, sMessage);
			}
		}
	}

	public static void toFile(byte[] traceInfo) {
		if (DEBUG) {
			File file = new File(FILE_NAME);
			try {
				file.createNewFile();
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream(file, true), MaxBufferSize);
				bufferedOutputStream.write(traceInfo);
				traceInfo = null;
				bufferedOutputStream.close();
			} catch (IOException e) {
				Trace.d(e.getMessage());
			}
		}
	}

}
