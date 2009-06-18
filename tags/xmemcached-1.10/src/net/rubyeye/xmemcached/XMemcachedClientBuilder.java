package net.rubyeye.xmemcached;

import com.google.code.yanf4j.config.Configuration;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import net.rubyeye.xmemcached.buffer.BufferAllocator;
import net.rubyeye.xmemcached.buffer.SimpleBufferAllocator;
import net.rubyeye.xmemcached.impl.ArrayMemcachedSessionLocator;

/**
 * Builder pattern.Configure XmemcachedClient's options,then build it
 *
 * @author dennis
 *
 */
public class XMemcachedClientBuilder implements MemcachedClientBuilder {

	private MemcachedSessionLocator sessionLocator = new ArrayMemcachedSessionLocator();
	private BufferAllocator bufferAllocator = new SimpleBufferAllocator();
	private Configuration configuration = XMemcachedClient
			.getDefaultConfiguration();
	private List<InetSocketAddress> addressList;

	public XMemcachedClientBuilder(List<InetSocketAddress> addressList) {
		this.addressList = addressList;
	}

	public XMemcachedClientBuilder() {
		this(null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.rubyeye.xmemcached.MemcachedClientBuilder#getSessionLocator()
	 */
	public MemcachedSessionLocator getSessionLocator() {
		return sessionLocator;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.rubyeye.xmemcached.MemcachedClientBuilder#setSessionLocator(net.rubyeye
	 * .xmemcached.MemcachedSessionLocator)
	 */
	public void setSessionLocator(MemcachedSessionLocator sessionLocator) {
		this.sessionLocator = sessionLocator;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.rubyeye.xmemcached.MemcachedClientBuilder#getBufferAllocator()
	 */
	public BufferAllocator getBufferAllocator() {
		return bufferAllocator;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.rubyeye.xmemcached.MemcachedClientBuilder#setBufferAllocator(net.
	 * rubyeye.xmemcached.buffer.BufferAllocator)
	 */
	public void setBufferAllocator(BufferAllocator bufferAllocator) {
		this.bufferAllocator = bufferAllocator;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.rubyeye.xmemcached.MemcachedClientBuilder#getConfiguration()
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.rubyeye.xmemcached.MemcachedClientBuilder#setConfiguration(com.google
	 * .code.yanf4j.config.Configuration)
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.rubyeye.xmemcached.MemcachedClientBuilder#build()
	 */
	public MemcachedClient build() throws IOException {
		return new XMemcachedClient(this.sessionLocator, this.bufferAllocator,
				this.configuration, this.addressList);
	}
}