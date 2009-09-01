package net.rubyeye.xmemcached;

import java.io.IOException;
import java.util.List;

import net.rubyeye.xmemcached.buffer.BufferAllocator;
import net.rubyeye.xmemcached.transcoders.Transcoder;

import com.google.code.yanf4j.config.Configuration;

/**
 * Builder pattern.Configure XmemcachedClient's options,then build it
 * 
 * @author dennis
 * 
 */
public interface MemcachedClientBuilder {

	/**
	 * 
	 * @return net.rubyeye.xmemcached.MemcachedSessionLocator
	 */
	public MemcachedSessionLocator getSessionLocator();

	/**
	 * Set the XmemcachedClient's session locator.Use
	 * ArrayMemcachedSessionLocator by default.If you want to choose consistent
	 * hash strategy,set it to KetamaMemcachedSessionLocator
	 * 
	 * @param sessionLocator
	 */
	public void setSessionLocator(MemcachedSessionLocator sessionLocator);

	public BufferAllocator getBufferAllocator();

	/**
	 * Set nio ByteBuffer's allocator.Use SimpleBufferAllocator by default.You
	 * can choose CachedBufferAllocator.
	 * 
	 * @param bufferAllocator
	 */
	public void setBufferAllocator(BufferAllocator bufferAllocator);

	/**
	 * Return the default networking's configuration,you can change them.
	 * 
	 * @return
	 */
	public Configuration getConfiguration();

	/**
	 * Set the XmemcachedClient's networking
	 * configuration(reuseAddr,receiveBufferSize,tcpDelay etc.)
	 * 
	 * @param configuration
	 */
	public void setConfiguration(Configuration configuration);

	/**
	 * Build MemcachedClient by current options.
	 * 
	 * @return
	 * @throws IOException
	 */
	public MemcachedClient build() throws IOException;

	/**
	 * In a high concurrent enviroment,you may want to pool memcached
	 * clients.But a xmemcached client has to start a reactor thread and some
	 * thread pools,if you create too many clients,the cost is very large.
	 * Xmemcached supports connection pool instreadof client pool.you can create
	 * more connections to one or more memcached servers,and these connections
	 * share the same reactor and thread pools,it will reduce the cost of
	 * system.
	 * 
	 * @param poolSize
	 *            pool size,default is 1
	 */
	public void setPoolSize(int poolSize);

	/**
	 * Set xmemcached's transcoder,it is used for seriailizing
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Transcoder getTranscoder();

	@SuppressWarnings("unchecked")
	public void setTranscoder(Transcoder transcoder);

	/**
	 * get xmemcached's command factory
	 * 
	 * @return
	 */
	public CommandFactory getCommandFactory();

	/**
	 * Add a state listener
	 * 
	 * @param stateListener
	 */
	public void addStateListener(MemcachedClientStateListener stateListener);

	/**
	 * Remove a state listener
	 * 
	 * @param stateListener
	 */
	public void removeStateListener(MemcachedClientStateListener stateListener);

	/**
	 * Set state listeners,replace current list
	 * 
	 * @param stateListeners
	 */
	public void setStateListeners(
			List<MemcachedClientStateListener> stateListeners);

	/**
	 * set xmemcached's command factory.Default is TextCommandFactory,which
	 * implements memcached text protocol.
	 * 
	 * @param commandFactory
	 */
	public void setCommandFactory(CommandFactory commandFactory);

}