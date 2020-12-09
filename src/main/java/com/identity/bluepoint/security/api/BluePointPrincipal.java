package com.identity.bluepoint.security.api;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import com.identity.bluepoint.util.DebugDumpable;
import com.identity.bluepoint.util.ShortDumpable;

public class BluePointPrincipal implements UserDetails, DebugDumpable, ShortDumpable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<Authorization> authorizations = new ArrayList<>();

    @Override
    public Collection<Authorization> getAuthorities() {
        return authorizations;
    }

	@Override
	public void shortDump(StringBuilder sb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String debugDump(int indent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
