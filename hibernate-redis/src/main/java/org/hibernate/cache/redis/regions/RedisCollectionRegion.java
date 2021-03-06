/*
 * Copyright 2011-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibernate.cache.redis.regions;

import java.util.Properties;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.redis.jedis.JedisClient;
import org.hibernate.cache.redis.strategy.RedisAccessStrategyFactory;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cfg.Settings;

/**
 * An collection region specific wrapper around an Redis.
 * <p/>
 * This implementation returns Redis specific access strategy instances for all the non-transactional access types.
 * Transactional access is not supported.
 *
 * @author sunghyouk.bae@gmail.com
 * @since 13. 4. 5. 오후 8:54
 */
public class RedisCollectionRegion extends RedisTransactionalDataRegion implements CollectionRegion {

    public RedisCollectionRegion(RedisAccessStrategyFactory accessStrategyFactory,
                                 JedisClient redis,
                                 String regionName,
                                 Settings settings,
                                 CacheDataDescription metadata,
                                 Properties props) {
        super(accessStrategyFactory, redis, regionName, settings, metadata, props);
    }

    @Override
    public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return getAccessStrategyFactory().createCollectionRegionAccessStrategy(this, accessType);
    }
}
