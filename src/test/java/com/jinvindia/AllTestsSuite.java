/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jinvindia;

import com.jinvindia.inventory.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@RunWith(JUnitPlatform.class)  // Required for IDE support
@Suite
@SuiteDisplayName("Test Suite")
//@SelectPackages({"com.jinvindia", "com.jinvindia.inventory"})
@SelectClasses({
    HrArtistRestTests.class,
    InventoryControllerTest.class,
    ProductServiceTests.class,
    NonRecalledProductServiceTest.class
})
public class AllTestsSuite {
    // This class remains empty - it's just a configuration class
}

/*
For Spring Boot tests, you have several options for @RunWith depending on your testing needs. Here are the most common approaches:

1. SpringRunner (JUnit 4) - Legacy Approach
java
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)  // For JUnit 4
@SpringBootTest
public class MySpringBootTest {
    // Test methods
}
2. SpringExtension (JUnit 5) - Modern Approach
java
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)  // For JUnit 5
@SpringBootTest
public class MySpringBootTest {
    // Test methods
}
3. Suite Runner (For Test Suites)
java
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestClass1.class,
    TestClass2.class,
    TestClass3.class
})
public class TestSuite {
    // This class remains empty
}
Key Differences:
Approach	When to Use	JUnit Version	Notes
SpringRunner	Legacy Spring Boot apps	JUnit 4	Being phased out
SpringExtension	New projects	JUnit 5 (Jupiter)	Default in modern Spring Boot
Suite	Running multiple test classes together	JUnit 4	For test suites
Recommendation for New Projects:
java
// For individual test classes
@ExtendWith(SpringExtension.class)  // JUnit 5
@SpringBootTest
public class MyTests {
    // ...
}

// For test suites (JUnit 5)
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    MyTests.class,
    OtherTests.class
})
public class AllTestsSuite {
}
Note: In Spring Boot 2.4+, you don't need to explicitly declare @ExtendWith(SpringExtension.class) if you're using @SpringBootTest - it's included automatically.
*/

/*
Creating a Test Suite for Spring Boot JUnit Tests
Here's how to create a test suite that runs all your Spring Boot JUnit test classes together:

1. Create a Test Suite Class
java
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SuiteDisplayName("Spring Boot Test Suite")
@SelectPackages("com.yourpackage") // Replace with your base package
public class AllTestsSuite {
    // This class remains empty
    // It's just a container for the suite configuration
}
2. Alternative: Explicitly Select Test Classes
If you prefer to explicitly list your test classes:

java
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SuiteDisplayName("Spring Boot Test Suite")
@SelectClasses({
    HackerRankArtistRestTests.class,
    AnotherTestClass.class,
    ServiceLayerTests.class
    // Add all your test classes here
})
public class AllTestsSuite {
}
3. Maven Configuration
Ensure your pom.xml has these dependencies:

xml
<dependencies>
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-suite-api</artifactId>
        <version>1.9.3</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
4. Running the Suite
You can run the suite in several ways:

From IDE:
Right-click the AllTestsSuite class and select "Run"

From Maven:
bash
mvn test -Dtest=AllTestsSuite
From Command Line:
bash
./mvnw test
Important Notes:
Spring Context: The @SpringBootTest annotation ensures the Spring context is loaded only once for all tests in the suite.

Test Isolation: If your tests modify the application context or database, consider adding:

java
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
Package Structure: Make sure all your test classes are in or under the package specified in @SelectPackages.

JUnit 5: This approach uses JUnit 5's @Suite (from junit-platform-suite-api), not the older JUnit 4 @RunWith.

Test Order: By default, tests run in deterministic but non-obvious order. If order matters, use @TestMethodOrder.

Would you like me to modify this approach for any specific requirements, such as:

Custom test ordering

Including/excluding certain test categories

Parallel test execution configuration
*/