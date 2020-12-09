package com.identity.bluepoint.security.api;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import com.identity.bluepoint.util.DebugDumpable;

public class Authorization implements GrantedAuthority, DebugDumpable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Collection<? extends String> getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String debugDump(int indent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}
