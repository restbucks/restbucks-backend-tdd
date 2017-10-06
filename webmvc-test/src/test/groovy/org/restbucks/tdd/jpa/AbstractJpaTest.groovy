package org.restbucks.tdd.jpa

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.Commit
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.PlatformTransactionManager

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@RunWith(SpringRunner)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfiguration)
@ActiveProfiles("test")
@Commit
abstract class AbstractJpaTest {

    @PersistenceContext
    protected EntityManager entityManager

    @Autowired
    protected PlatformTransactionManager transactionManager


}
