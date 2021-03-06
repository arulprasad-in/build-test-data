import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.junit.Test

@Rollback
@Integration
class DomainTestDataServiceBooleanTests implements DomainTestDataServiceBase {
    @Test
    void testBooleanDefaultGroovyTruthFalseOk() {
        def domainClass = createDomainClass("""
            class TestDomain {
                Long id
                Long version
                Boolean testProperty
            }
        """)

        def domainObject = domainClass.build()
        assert domainObject != null
        assert domainObject.testProperty == false
    }

    @Test
    void testBooleanManuallySetValues() {
        def domainClass = createDomainClass("""
            class TestDomain {
                Long id
                Long version
                Boolean testProperty
            }
        """)

        def domainObject = domainClass.build(testProperty: true)
        assert domainObject != null
        assert domainObject.testProperty == true

        domainObject = domainClass.build(testProperty: false)
        assert domainObject != null
        assert domainObject.testProperty == false

    }

    @Test
    void testBooleanNullable() {
        def domainClass = createDomainClass("""
            class TestDomain {
                Long id
                Long version
                Boolean testProperty
                static constraints = {
                    testProperty(nullable: true)
                }
            }
        """)

        def domainObject = domainClass.build()

        assert domainObject != null
        assert domainObject.testProperty == null
    }

    @Test
    void testBooleanNotNullable() {
        def domainClass = createDomainClass("""
            class TestDomain {
                Long id
                Long version
                Boolean testProperty
            }
        """)

        def domainObject = domainClass.build()

        assert domainObject != null
        assert domainObject.testProperty != null
    }
}
