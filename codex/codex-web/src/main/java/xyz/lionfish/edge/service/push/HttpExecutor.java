package xyz.lionfish.edge.service.push;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;


/**
 *
 * @author Kevendra Patidar
 */
public class HttpExecutor implements InitializingBean {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(HttpExecutor.class);

	/* ************************************ Instance Fields ************************************ */
	private int maxTotal;
	private int defaultMaxPerRoute;
	private int soTimeout;
	private int soLinger;
	private int connectTimeout;

	private CloseableHttpClient client;

	/* ************************************ Public Methods ************************************ */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.client = HttpClients.custom()
				.setConnectionManager(this.buildConnectionManager())
				.setDefaultRequestConfig(this.buildRequestConfig())
				.build();
	}
	public String post(final String link, final String mimeType, final String payload, final Map<String, String> header){
//		post.setEntity(new StringEntity("{\"username\":\"" + this.apiusername + "\",\"password\":\"" + this.apipassword + "\"}",
//                ContentType.create("application/json")));
		final HttpEntity entity = new StringEntity(payload, ContentType.create(mimeType));
		return post(link, mimeType, entity, header);
	}
	public String post(final String link, final String mimeType, final List<NameValuePair> nvps){
		//final UrlEncodedFormEntity sendentity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
		final HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
		return post(link, mimeType, entity, null);
	}
	public String post(final String link, final String mimeType, final HttpEntity inputEntity, final Map<String, String> header){
		String pageHTML = null;
		CloseableHttpResponse response = null;
		InputStream stream = null;
		LOG.debug("Retrieving page preview for {}", link);
		try {
			final URL url = new URL(link);
			final HttpPost post = new HttpPost(url.toURI());
			post.setEntity(inputEntity);
			post.setHeader("Content-Type", mimeType);
			if( ! CollectionUtils.isEmpty(header)){
				for(final Entry<String, String> entry : header.entrySet()){
					post.setHeader(entry.getKey(), entry.getValue());
				}
			}
			response = this.client.execute(post);
			final HttpEntity entity = response.getEntity();
			stream = entity.getContent();

			pageHTML = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (final Exception e) {
			LOG.error("error post link {} \n{}", link, e);
			e.printStackTrace();
		} finally {
			//client.getConnectionManager().shutdown();
			if (stream != null) {
				try {
					stream.close();
				} catch (final Exception e) {
					LOG.warn("error closing input stream", e);
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (final Exception e) {
					LOG.warn("error closing http response", e);
				}
			}
		}
		return pageHTML;
	}
	public String get(final String link, final String type){
		String pageHTML = null;
		CloseableHttpResponse response = null;
		InputStream stream = null;

		LOG.debug("Retrieving page preview for {}", link);

		try {
			final HttpGet get = new HttpGet(link);
			get.setHeader("Content-Type", type);

			response = this.client.execute(get);
			final HttpEntity entity = response.getEntity();
			stream = entity.getContent();

			pageHTML = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (final Exception e) {
		} finally {
			//client.getConnectionManager().shutdown();
			if (stream != null) {
				try {
					stream.close();
				} catch (final Exception e) {
					LOG.warn("error closing input stream", e);
				}
			}
			if (response != null) {
				try {
					response.close();
				} catch (final Exception e) {
					LOG.warn("error closing http response", e);
				}
			}
		}
		return pageHTML;
	}

	/* ************************************ Protected Methods ************************************ */
	/**
	 * Build a ConnectionManager based on the settings in executor
	 * - made a helper function for easier testing,
	 *
	 * @return A new PoolingHttpClientConnectionManager with the expected settings.
	 */
	protected PoolingHttpClientConnectionManager buildConnectionManager() {
		final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(this.maxTotal);
		cm.setDefaultMaxPerRoute(this.defaultMaxPerRoute);
		cm.setDefaultSocketConfig(SocketConfig.custom()
				.setSoLinger(this.soLinger)
				.setSoTimeout(this.soTimeout)
				.build());
		return cm;
	}
	/**
	 * Build a RequestConfig based on the settings in Executor
	 * - made a helper function for easier testing
	 *
	 * @return A new RequestConfig with the expected settings.
	 */
	protected RequestConfig buildRequestConfig() {
		return RequestConfig.custom()
				.setConnectTimeout(this.connectTimeout)
				.setStaleConnectionCheckEnabled(true)
				.build();
	}

	/* ************************************ Getters and Setters ************************************ */
	public int getDefaultMaxPerRoute() {
		return this.defaultMaxPerRoute;
	}
	public void setDefaultMaxPerRoute(final int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}
	public int getSoTimeout() {
		return this.soTimeout;
	}
	public void setSoTimeout(final int soTimeout) {
		this.soTimeout = soTimeout;
	}
	public int getSoLinger() {
		return this.soLinger;
	}
	public void setSoLinger(final int soLinger) {
		this.soLinger = soLinger;
	}
	public int getConnectTimeout() {
		return this.connectTimeout;
	}
	public void setConnectTimeout(final int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getMaxTotal() {
		return this.maxTotal;
	}
	public void setMaxTotal(final int maxTotal) {
		this.maxTotal = maxTotal;
	}

}//EOF
