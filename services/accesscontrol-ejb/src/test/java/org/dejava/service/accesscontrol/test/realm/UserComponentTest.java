package org.dejava.service.accesscontrol.test.realm;


/**
 * Tests for the shiro EJB realm.
 */
public class UserComponentTest {
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
	// // Defines and returns the archive definition.
	// return ShrinkWrap
	// .create(WebArchive.class, "test.war")
	// .addPackages(true, "org.dejava.properties")
	// .addAsLibraries(
	// dependencyResolver.artifacts("org.dejava.component:security",
	// "org.dejava.component:ejb", "org.dejava.component:exception",
	// "org.dejava.component:i18n", "org.dejava.component:reflection",
	// "org.dejava.component:validation", "com.restfb:restfb").resolveAsFiles())
	// .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
	// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	// .addAsWebInfResource("test-ds.xml", "test-ds.xml");
	// }
	//
	// /**
	// * The user EJB service.
	// */
	// @Inject
	// @AccessControlCtx
	// private UserComponent userComponent;
	//
	// /**
	// * Assert that there are no users persisted.
	// */
	// @Before
	// public void assertNoUsersPersisted() {
	// // Gets all users.
	// final Collection<User> allUsers = userComponent.getAll(null, null);
	// // Assert that there are no users persisted.
	// Assert.assertTrue(allUsers.isEmpty());
	// }
	//
	// /**
	// * Removes all test users.
	// */
	// @After
	// public void removeTestUsers() {
	// // Gets all users.
	// final Collection<User> allEntities = userComponent.getAll(null, null);
	// // Removes all persisted users.
	// userComponent.remove(allEntities);
	// }
	//
	// /**
	// * The facebook user id to be used in the tests.
	// */
	// private static final String FB_USER_ID = "fbUserId";
	//
	// /**
	// * The facebook user name to be used in the tests.
	// */
	// private static final String FB_USER_NAME = "fbUserName";
	//
	// /**
	// * The facebook user email to be used in the tests.
	// */
	// private static final String FB_USER_EMAIL = "fbUserEmail@dejava.org";
	//
	// /**
	// * Gets the facebook user to be used in the tests.
	// *
	// * @return The facebook user to be used in the tests.
	// * @throws InvocationException
	// * If the values for the user attributes cannot be set.
	// */
	// private com.restfb.types.User getFBTestUser() throws InvocationException {
	// // Creates a new facebook user.
	// final com.restfb.types.User fbUser = new com.restfb.types.User();
	// // Creates the mirror for the facbook user class.
	// final ClassMirror<com.restfb.types.User> fbUserClassMirror = new ClassMirror<com.restfb.types.User>(
	// com.restfb.types.User.class);
	// // Sets the facebook user id.
	// fbUserClassMirror.getField("id").setValue(fbUser, FB_USER_ID, true, true);
	// // Sets the facebook user name.
	// fbUserClassMirror.getField("username").setValue(fbUser, FB_USER_NAME, true, true);
	// // Sets the facebook user email.
	// fbUserClassMirror.getField("email").setValue(fbUser, FB_USER_EMAIL, true, true);
	// // Returns the facebook user.
	// return fbUser;
	// }
	//
	// /**
	// * Tests the getByFacebookUser method using a user with the facebook principal previously added.
	// *
	// * @throws InvocationException
	// * If the values for the user attributes cannot be set.
	// */
	// @Test
	// public void testGetByFacebookUserIdSuccess() throws InvocationException {
	// // Gets the facebook user.
	// final com.restfb.types.User fbUser = getFBTestUser();
	// // Creates a new user with the same info.
	// final User user = new User(fbUser);
	// // Removes the email principal (so the only test will over the facebook principal).
	// user.getPrincipals().remove(user.getPrincipal(Email.class));
	// // Persists the user.
	// userComponent.addOrUpdate(user);
	// // Tries to get the a user by the facebook user.
	// final User retrievedUser = userComponent.getByFacebookUser(fbUser);
	// // Asserts that the user is retrieved correctly.
	// Assert.assertNotNull(retrievedUser);
	// }
	//
	// /**
	// * Tests the getByFacebookUser method using a user with the email principal previously added.
	// *
	// * @throws InvocationException
	// * If the values for the user attributes cannot be set.
	// */
	// @Test
	// public void testGetByFacebookUserEmailSuccess() throws InvocationException {
	// // Gets the facebook user.
	// final com.restfb.types.User fbUser = getFBTestUser();
	// // Creates a new user with the same info.
	// final User user = new User(fbUser);
	// // Removes the email principal (so the only test will over the email principal).
	// user.getPrincipals().remove(user.getPrincipal(Facebook.class));
	// // Persists the user.
	// userComponent.addOrUpdate(user);
	// // Tries to get the a user by the facebook user.
	// final User retrievedUser = userComponent.getByFacebookUser(fbUser);
	// // Asserts that the user is retrieved correctly.
	// Assert.assertNotNull(retrievedUser);
	// }
	//
	// /**
	// * Tests the getByFacebookUser method using a user that has not been added yet.
	// *
	// * @throws InvocationException
	// * If the values for the user attributes cannot be set.
	// */
	// @Test
	// public void testGetByFacebookUserEmailFailure() throws InvocationException {
	// // Gets the facebook user.
	// final com.restfb.types.User fbUser = getFBTestUser();
	// // Creates a new user with the same info.
	// final User user = new User(fbUser);
	// // Removes the email and facebook principal (so the user cannot be matched).
	// user.getPrincipals().remove(user.getPrincipal(Email.class));
	// user.getPrincipals().remove(user.getPrincipal(Facebook.class));
	// // Persists the user.
	// userComponent.addOrUpdate(user);
	// // Tries to get the a user by the facebook user.
	// final User retrievedUser = userComponent.getByFacebookUser(fbUser);
	// // Asserts that no user is retrieved.
	// Assert.assertNull(retrievedUser);
	// }
}
