package xyz.lionfish.edge.service.push;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author Kevendra Patidar
 */
@Configuration
public class ApiConfiguration {

	/* ************************************ Public Methods ************************************ */
	/*
	@Bean
	public DiscRetriever discRetriever() {
		DiscRetriever retriever = new DiscRetriever();

		//Determines the timeout in milliseconds until a connection is established.
		retriever.setConnectTimeout(15000);

		//Determines the default socket timeout value for non-blocking I/O operations.
		//The default socket timeout (SO_TIMEOUT) in milliseconds which is the timeout for waiting for data.
		//A timeout value of zero is interpreted as an infinite timeout.
		retriever.setSoTimeout(30000);

		// This option disables/enables immediate return from a close() of a TCP Socket.
		// Enabling this option with a non-zero Integer timeout means that a close()
		// will block pending the transmission and acknowledgement of all data written
		// to the peer, at which point the socket is closed gracefully.
		// Value 0 implies that the option is disabled.
		// Value -1 implies that the JRE default is used.
		retriever.setSoLinger(-1);

		//We used a pooled connection behind the scenes
		//how many total/per host at one time...
		retriever.setDefaultMaxPerRoute(10);
		retriever.setMaxTotal(40);


		return retriever;
	}
	*/
	@Bean
	public HttpExecutor httpExecutor() {
		final HttpExecutor executor = new HttpExecutor();

		//Determines the timeout in milliseconds until a connection is established.
		executor.setConnectTimeout(15000);

		//Determines the default socket timeout value for non-blocking I/O operations.
		//The default socket timeout (SO_TIMEOUT) in milliseconds which is the timeout for waiting for data.
		//A timeout value of zero is interpreted as an infinite timeout.
		executor.setSoTimeout(30000);

		// This option disables/enables immediate return from a close() of a TCP Socket.
		// Enabling this option with a non-zero Integer timeout means that a close()
		// will block pending the transmission and acknowledgement of all data written
		// to the peer, at which point the socket is closed gracefully.
		// Value 0 implies that the option is disabled.
		// Value -1 implies that the JRE default is used.
		executor.setSoLinger(-1);

		//We used a pooled connection behind the scenes
		//how many total/per host at one time...
		executor.setDefaultMaxPerRoute(10);
		executor.setMaxTotal(40);

		return executor;
	}

}
