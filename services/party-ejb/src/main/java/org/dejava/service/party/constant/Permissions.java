package org.dejava.service.party.constant;

/**
 * Permissions.
 */
public final class Permissions {

	/**
	 * Party id wildcard.
	 */
	public static final String PARTY_ID_WC = "#{partyId}";

	/**
	 * The party administrator permission.
	 */
	public static final String PARTY_ADMIN = "party.admin." + PARTY_ID_WC;
}
