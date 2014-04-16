package com.fpdbsampler;

/**
 * this class plays the role of controller in MVC design pattern.
 * it performs all data manipulation tasks.
 * @author Aaron Yu
 *
 */
public class FPSamplingSession
{

	private SessionState sessionState;
	private String ServerAdr;
	private String PhoneMac;
	
	private static FPSamplingSession singleton = null;
	
	private FPSamplingSession()
	{
		sessionState = SessionState.SessionInvalid;
		ServerAdr = "";
		PhoneMac = "";
	}
	
	/**
	 * get singleton instance of session, and register to web server
	 * @param serverip server IP info
	 * @return null if not able to start connection with web server
	 */
	public static FPSamplingSession initialize(String serverip, String mac)
	{
		if(singleton == null)
		{
			singleton = new FPSamplingSession();
		}
		if(!singleton.registerSession(serverip, mac))
		{
			//if not able to connect server, clear singleton
			singleton = null;
		}
		return singleton;
	}
	
	/**
	 * get initialized singleton
	 * @return
	 */
	public static FPSamplingSession getSingleton()
	{
		return singleton;
	}

	/**
	 * start connection with server, send MAC info to server
	 * when success, update state machine
	 * @param serverip
	 * @return
	 */
	private boolean registerSession(String serverip, String mac)
	{
		ServerAdr = serverip;
		PhoneMac = mac;
		// TODO implement regitsetration
		return false;
	}
	

	/**
	 * state machine of session is 
	 * invalid -> registered -> floor loaded -> map loaded -> fp point loaded -> sample ready
	 *                                                                            ^         |
	 *                                                                            |         v
	 *                                                                          sample in progress
	 * 
	 * @author Aaron Yu
	 *
	 */
	public enum SessionState
	{
		SessionInvalid,
		SessionRegistered,
		FloorInfoLoaded,
		MapInfoLoaded,
		FPPointInfoLoaded,
		SamplingInProgress,
		SamplingReady
	}
}
