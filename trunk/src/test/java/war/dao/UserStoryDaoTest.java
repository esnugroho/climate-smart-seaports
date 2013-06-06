/**
 * Copyright (c) 2013, RMIT University, Australia.
 * All rights reserved.
 * 
 * This code is under the BSD license. See 'license.txt' for details.
 * Project hosted at: https://code.google.com/p/climate-smart-seaports/
 */
package war.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.UserLoginService;

import war.model.Region;
import war.model.Seaport;
import war.model.User;
import war.model.UserStory;

/**
 * Test class for the user stories DAO
 * @author Guillaume Prevost
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserStoryDaoTest {

	User userForTest;
	
	User userWithoutStory;
	
	UserStory userStoryForTest;
	
	@Autowired
	private UserStoryDao userStoryDao;

	/**
	 * Method executed before starting the unit tests to prepared the data
	 */
	@Before
	public void prepareData() {		
		userForTest = new User("testuser1", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser1", "testuser1");
		userWithoutStory = new User("testuser4", "password", true, true, UserLoginService.ROLE_USER, "email@company.com", "testuser4", "testuser4");
		
		userStoryForTest = new UserStory("User 1 WorkBoard", "User story Purpose", "active", "private", userForTest, 
				new Seaport("AUSYD", "Sydney Harbour", new Region("East Coast South", "")), null);
	}

	/**
	 * Test for the find method
	 * There should be a story in the test DB matching the fields
	 */
	@Test
	public void userStoryfindTest() {
		UserStory story = userStoryDao.find(1);

		Assert.assertNotNull(story);
		
		Assert.assertEquals("User 1 Workboard", story.getName());
		Assert.assertEquals("active", story.getMode());
		Assert.assertEquals("private", story.getAccess());
		Assert.assertEquals("testuser1", story.getOwner().getUsername());
		Assert.assertEquals("East Coast South", story.getSeaport().getRegion().getName());
	}
	
	/**
	 * Test for the find method
	 * The method should throw an exception since ID 9999 doesn't exist
	 */
	@Test(expected = NoResultException.class)
	public void userStoryfindUnexistingStoryTest() {
		userStoryDao.find(9999); // Non-existing ID
	}
	
	/**
	 * Test for getAllStories method
	 * There should be 6 stories in the Test DB
	 */
	@Test
	public void getAllStoriesTest() {
		List<UserStory> stories = userStoryDao.getAllStories();
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(6, stories.size());
	}
	
	/**
	 * Test for getUserStories. It should retrieve a list of 3 PASSIVE user stories
	 */
	@Test
	public void getUserStoriesTest() {
		List<UserStory> stories = userStoryDao.getUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(3, stories.size());
	}
	
	/**
	 * Test for getUserStories. It should retrieve a list of 0 user story
	 */
	@Test
	public void getUserStoriesNoStoryTest() {
		List<UserStory> stories = userStoryDao.getUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPrivateUserStories. It should retrieve a list of 1 PASSIVE PRIVATE user story
	 */
	@Test
	public void getPrivateUserStoriesTest() {
		List<UserStory> stories = userStoryDao.getPrivateUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(1, stories.size());
	}
	
	/**
	 * Test for getPrivateUserStories. It should retrieve a list of 0 user story
	 */
	@Test
	public void getPrivateUserStoriesNoStoryTest() {
		List<UserStory> stories = userStoryDao.getPrivateUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPublicUserStories. It should retrieve a list of 2 PASSIVE PUBLIC user story
	 */
	@Test
	public void getPublicUserStoriesTest() {
		List<UserStory> stories = userStoryDao.getPublicUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(2, stories.size());
	}
	
	/**
	 * Test for getPublicUserStories. It should retrieve a list of 0 user story
	 */
	@Test
	public void getPublicUserStoriesNoStoryTest() {
		List<UserStory> stories = userStoryDao.getPublicUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 1 PASSIVE PUBLISHED user story
	 */
	@Test
	public void getPublishedUserStoriesTest() {
		List<UserStory> stories = userStoryDao.getPublishedUserStories(userForTest);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(1, stories.size());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 0 user stories
	 */
	@Test
	public void getPublishedUserStoriesNoStoryTest() {
		List<UserStory> stories = userStoryDao.getPublishedUserStories(userWithoutStory);
		
		Assert.assertNotNull(stories);
		Assert.assertEquals(0, stories.size());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 1 PASSIVE PUBLISHED user story
	 */
	@Test
	public void getWorkBoardTest() {
		UserStory workboard = userStoryDao.getWorkboard(userForTest);
		
		Assert.assertNotNull(workboard);
		Assert.assertEquals("User 1 Workboard", workboard.getName());
		Assert.assertEquals("active", workboard.getMode());
		Assert.assertEquals("private", workboard.getAccess());
	}
	
	/**
	 * Test for getPublishedUserStories. It should retrieve a list of 0 user stories
	 */
	@Test
	public void getWorkBoardNoWorkboardTest() {
		UserStory workboard = userStoryDao.getWorkboard(userWithoutStory);
		
		Assert.assertNull(workboard);
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void userSaveNewUserStoryTest() {
		UserStory savedUserStory = userStoryDao.save(new UserStory("test", "Purpose", "active", "private", userForTest, 
				new Seaport("AUSYD", "Sydney Harbour", new Region("East Coast South", "")), null));
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("test", savedUserStory.getName());
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("active", savedUserStory.getMode());
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("private", savedUserStory.getAccess());
		
		Assert.assertNotNull(savedUserStory.getOwner());
		Assert.assertEquals("testuser1", savedUserStory.getOwner().getUsername());
		
		Assert.assertNotNull(savedUserStory.getSeaport().getRegion());
		Assert.assertEquals("East Coast South", savedUserStory.getSeaport().getRegion().getName());
		
		Assert.assertNull(savedUserStory.getDataElements());
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void userSaveStoryOnlyNameTest() {
		UserStory userStory = new UserStory();
		userStory.setName("test");
		UserStory savedUserStory = userStoryDao.save(userStory);
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("test", savedUserStory.getName());
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("active", savedUserStory.getMode());
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("private", savedUserStory.getAccess());
		
		Assert.assertNull(savedUserStory.getOwner());
		Assert.assertNull(savedUserStory.getSeaport());
		Assert.assertNull(savedUserStory.getDataElements());
	}
	
	/**
	 * Test for save.
	 */
	@Test
	public void userSaveExistingUserStoryTest() {
		UserStory savedUserStory = userStoryDao.save(userStoryForTest);
	
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("User 1 WorkBoard", savedUserStory.getName());
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("active", savedUserStory.getMode());
		
		Assert.assertNotNull(savedUserStory.getName());
		Assert.assertEquals("private", savedUserStory.getAccess());
	}
	
	/**
	 * Test for save. It should throw an exception since the given user story is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void userSaveNullTest() {
		userStoryDao.save(null);
	}
	
	/**
	 * Test for save. It should throw an exception since the given user story name is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void userSaveEmptyTest() {
		userStoryDao.save(new UserStory());
	}
	
	/**
	 * Test for delete.
	 */
	@Test
	public void userDeleteStoryTest() {
		UserStory story = userStoryDao.find(1);
		Assert.assertNotNull(story);
		
		userStoryDao.delete(story);
	}
	
	/**
	 * Test for delete. It should throw an exception since the given user story is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void userDeleteNullTest() {
		userStoryDao.delete(null);
	}
}
