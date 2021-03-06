package org.dejava.service.accesscontrol.test.realm;


/**
 * Tests for the shiro EJB realm.
 */
public class EJBRealmTest {

	// /**
	// * The web application source path.
	// */
	// public static final String WEBAPP_SRC = "src/main/webapp";
	//
	// /**
	// * Defines what should be done during deployment (before tests).
	// *
	// * @return The configuration for the archive.
	// */
	// @Deployment
	// public static Archive<?> createTestArchive() {
	// // Gets the maven dependency resolver (for the regular libs).
	// final MavenDependencyResolver dependencyResolver = DependencyResolvers.use(
	// MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
	// // Gets the maven dependency resolvers (for the ejb modules).
	// final MavenDependencyResolver dependencyResolver2 = DependencyResolvers.use(
	// MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
	// final MavenDependencyResolver dependencyResolver3 = DependencyResolvers.use(
	// MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
	// final MavenDependencyResolver dependencyResolver4 = DependencyResolvers.use(
	// MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
	// final MavenDependencyResolver dependencyResolver5 = DependencyResolvers.use(
	// MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
	// // Gets the EJB jars.
	// final JavaArchive accessControlEjbJar = dependencyResolver2
	// .artifact("org.dejava.service:accesscontrol-ejb").resolveAs(JavaArchive.class).iterator()
	// .next();
	// final JavaArchive partyEjbJar = dependencyResolver3.artifact("org.dejava.service:party-ejb")
	// .resolveAs(JavaArchive.class).iterator().next();
	// final JavaArchive contactEjbJar = dependencyResolver4.artifact("org.dejava.service:contact-ejb")
	// .resolveAs(JavaArchive.class).iterator().next();
	// final JavaArchive placeEjbJar = dependencyResolver5.artifact("org.dejava.service:place-ejb")
	// .resolveAs(JavaArchive.class).iterator().next();
	// // Removes the persistence.xml from the jars.
	// accessControlEjbJar.delete("META-INF/persistence.xml");
	// partyEjbJar.delete("META-INF/persistence.xml");
	// contactEjbJar.delete("META-INF/persistence.xml");
	// placeEjbJar.delete("META-INF/persistence.xml");
	// // Defines and returns the archive definition.
	// return ShrinkWrap
	// .create(WebArchive.class, "test.war")
	// .merge(accessControlEjbJar, "WEB-INF/classes")
	// .merge(partyEjbJar, "WEB-INF/classes")
	// .merge(contactEjbJar, "WEB-INF/classes")
	// .merge(placeEjbJar, "WEB-INF/classes")
	// .addPackages(true, "org.dejava.service.accesscontrol")
	// .addAsLibraries(
	// dependencyResolver.artifacts("org.dejava.component:security",
	// "org.dejava.component:ejb", "org.dejava.component:faces",
	// "org.apache.shiro:shiro-core", "org.apache.shiro:shiro-web",
	// "org.apache.shiro:shiro-ehcache", "com.restfb:restfb",
	// "com.google.api-client:google-api-client",
	// "com.google.http-client:google-http-client-jackson2").resolveAsFiles())
	// .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
	// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	// .addAsWebInfResource("test-ds.xml", "test-ds.xml")
	// .addAsWebInfResource("shiro.ini", "shiro.ini")
	// .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
	// .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/web.xml"));
	// }
	//
	// /**
	// * User service.
	// */
	// @Inject
	// @AccessControlCtx
	// private UserComponent userComponent;
	//
	// /**
	// * Some users to be used in the tests.
	// */
	// private static final User[] SOME_USERS = { new User("rvcoutinho", "rvcoutinho@gmail.com", "12345") };
	//
	// /**
	// * Some other users to be used in the tests.
	// */
	// private static final User[] SOME_OTHER_USERS = { new User("rvcoutinho", "rvcoutinho@gmail.com",
	// "123456") };
	//
	// /**
	// * Adds some users (database) to be used in the tests.
	// */
	// @Before
	// public void addSomeUsers() {
	// // For each user.
	// for (final User currentUser : SOME_USERS) {
	// // Tries to add the current user via the EJB user service.
	// userComponent.addOrUpdate(currentUser);
	// }
	// }
	//
	// /**
	// * Removes the users (database) used in the tests.
	// */
	// @After
	// public void removeSomeUsers() {
	// // Tries to remove all users.
	// userComponent.remove(userComponent.getAll(null, null));
	// }
	//
	// /**
	// * Logs the current user out.
	// */
	// @After
	// public void logout() {
	// // Gets the current logged user.
	// final Subject loggedUser = SecurityUtils.getSubject();
	// // Tries to logout the user.
	// loggedUser.logout();
	// }
	//
	// /**
	// * Gets a username/password token for the given user.
	// *
	// * @param user
	// * User to get the token for.
	// * @param emailAsUserName
	// * If the email should be used as user name.
	// * @return A username/password token for the given user.
	// */
	// private UsernamePasswordToken getUserToken(final User user, final Boolean emailAsUserName) {
	// // The user name to be used in the login.
	// final String userName;
	// // If the email should be used as the user name.
	// if (emailAsUserName) {
	// // Gets the user name.
	// userName = user.getPrincipal(Email.class).getEmail();
	// }
	// // Otherwise.
	// else {
	// // Gets the user name.
	// userName = user.getPrincipal(Name.class).getName();
	// }
	// // Gets the user password.
	// final String userPassword = user.getCredentials(Password.class).getRawPassword();
	// // Returns a new username/password token.
	// return new UsernamePasswordToken(userName, userPassword);
	// }
	//
	// /**
	// * Tests a regular login (using the user name) attempt.
	// */
	// @Test
	// public void testLoginNameSuccess() {
	// // Gets the current logged user.
	// final Subject loggedUser = SecurityUtils.getSubject();
	// // Tries to login the user.
	// loggedUser.login(getUserToken(SOME_USERS[0], false));
	// }
	//
	// /**
	// * Tests a regular login (using the user email) attempt.
	// */
	// @Test
	// public void testLoginEmailSuccess() {
	// // Gets the current logged user.
	// final Subject loggedUser = SecurityUtils.getSubject();
	// // Tries to login the user.
	// loggedUser.login(getUserToken(SOME_USERS[0], true));
	// }
	//
	// /**
	// * Tests a login (using the user name) attempt with wrong credentials.
	// */
	// @Test(expected = AuthenticationException.class)
	// public void testLoginNameWrongCredentials() {
	// // Gets the current logged user.
	// final Subject loggedUser = SecurityUtils.getSubject();
	// // Tries to login the user.
	// loggedUser.login(getUserToken(SOME_OTHER_USERS[0], false));
	// }
	//
	// /**
	// * Tests a login (using the user email) attempt with wrong credentials.
	// */
	// @Test(expected = AuthenticationException.class)
	// public void testLoginEmailWrongCredentials() {
	// // Gets the current logged user.
	// final Subject loggedUser = SecurityUtils.getSubject();
	// // Tries to login the user.
	// loggedUser.login(getUserToken(SOME_OTHER_USERS[0], true));
	// }
	//
	// /**
	// * Tests a login attempt with a null token.
	// */
	// @Test(expected = IllegalArgumentException.class)
	// public void testLoginNullToken() {
	// // Gets the current logged user.
	// final Subject loggedUser = SecurityUtils.getSubject();
	// // Tries to login the user.
	// loggedUser.login(null);
	// }

}
