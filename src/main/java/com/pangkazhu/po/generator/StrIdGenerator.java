package com.pangkazhu.po.generator;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RadixUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2025/6/19 15:10
 */
public class StrIdGenerator implements IdentifierGenerator {
	private static final Snowflake snowflake = new Snowflake();

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return RadixUtil.encode(RadixUtil.RADIXS_SHUFFLE_34, snowflake.nextId());
	}
}
