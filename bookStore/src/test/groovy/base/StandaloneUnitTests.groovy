package base

import grails.buildtestdata.UnitTestDataBuilder
import org.junit.Test
import standalone.Standalone

class StandaloneUnitTests implements UnitTestDataBuilder {
    @Test
    void testNullableBelongsToNotFollowed() {
        def standalone = Standalone.build() // standalone.Standalone has a "parent" property on it that is nullable (otherwise it'd get in an infinite loop)
        assert standalone
        assert standalone.id
        assert !standalone.parent
        assert standalone.emailAddress != null
    }

    @Test
    void testBuildStandalonePassAllVariables() {
		def created = new Date()
		def obj = Standalone.build(name: "Foo", age: 14, created: created, emailAddress: "foo@bar.com")
		assertValidDomainObject(obj)

		assert obj.name == "Foo"
		assert obj.age == 14
		assert obj.created == created
        assert "foo@bar.com" == obj.emailAddress
    }

    @Test
    void testBuildStandalonePassNoVariables() {
		def obj = Standalone.build()
		assertValidDomainObject(obj)

		assert obj.name  // by default it just uses the property name for the value for strings == "name"
		assert obj.created
		assert obj.age == 0
    }

    private void assertValidDomainObject(domainObject) {
	    assert domainObject
	    assert domainObject.id
	    assert domainObject.errors.errorCount == 0
    }
}
