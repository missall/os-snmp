package cn.bstar.gale.boss.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;  
import java.text.MessageFormat;
import java.util.ArrayList;  
import java.util.List;
import org.apache.log4j.Logger;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import java.lang.InterruptedException;

public class Ssh {
	
	private static Logger logger = Logger.getLogger(Ssh.class);
	
	//milliseconds
	private static final int SSH_CONN_TIME_OUT = 1000;
	
	//milliseconds
	private static final int SSH_KEX_TIME_OUT = 1000;

	public static String sshCmd(String hostname, String username,
			String password, String cmd) {
		String output = "";
		try {
			/* Create a connection instance */
			Connection conn = new Connection(hostname);
			/* Now connect */
			conn.connect(null, SSH_CONN_TIME_OUT, SSH_KEX_TIME_OUT);
			logger.info("connect ok");
			/*
			 * Authenticate. If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 */
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);
			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			System.out.println("Authentication ok");
			/* Create a session */
			Session sess = conn.openSession();
			sess.execCommand(cmd);
			logger.info("Here is some information about the remote host:");
			/*
			 * This basic example does not handle stderr, which is sometimes
			 * dangerous (please read the FAQ).
			 */
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				output = new String(line);
			}
			/* Show exit status, if available (otherwise "null") */
			System.out.println("ExitCode: " + sess.getExitStatus());
			/* Close this session */
			sess.close();
			/* Close the connection */
			conn.close();
			return output;
		} catch (IOException e) {
			e.printStackTrace(System.err);
			// System.exit(2);
			return "error";
		}
	}

	public static Boolean validateSsh(String hostname) {
		Boolean flag = false;
		Connection conn = null;
		try {
			conn = new Connection(hostname);
			//参数说明conn.connect(ServerHostKeyVerifier verifier, int connectTimeout, int kexTimeout)
			conn.connect(null, SSH_CONN_TIME_OUT, SSH_KEX_TIME_OUT);
			flag = true;
			return flag;
		} catch (IOException e) {
			logger.info("There was a problem while connecting to "+hostname+" with SSH Protocal");
			return flag;
		} finally {
			conn.close();
		}
	}
	 
}
