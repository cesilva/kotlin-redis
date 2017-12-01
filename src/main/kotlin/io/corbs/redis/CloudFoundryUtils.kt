package io.corbs.redis

import cworks.json.Json
import java.util.*


object CloudFoundryUtils {

    val vcapApplication: Map<String, Any>
        get() = getInfo("VCAP_APPLICATION")

    val vcapServices: Map<String, Any>
        get() = getInfo("VCAP_SERVICES")

    val vcapAppHost: Map<String, Any>
        get() = getInfo("VCAP_APP_HOST")

    val cfInstanceAddr: Map<String, Any>
        get() = getInfo("CF_INSTANCE_ADDR")

    val cfInstanceGuid: Map<String, Any>
        get() = getInfo("CF_INSTANCE_GUID")

    val cfInstanceIndex: Map<String, Any>
        get() = getInfo("CF_INSTANCE_INDEX")

    val cfInstanceIp: Map<String, Any>
        get() = getInfo("CF_INSTANCE_IP")

    val cfInstanceInternalIp: Map<String, Any>
        get() = getInfo("CF_INSTANCE_INTERNAL_IP")

    val cfInstancePort: Map<String, Any>
        get() = getInfo("CF_INSTANCE_PORT")

    val cfInstancePorts: Map<String, Any>
        get() = getInfo("CF_INSTANCE_PORTS")

    val cfHome: Map<String, Any>
        get() = getInfo("HOME")

    val cfMemoryLimit: Map<String, Any>
        get() = getInfo("MEMORY_LIMIT")

    val cfPwd: Map<String, Any>
        get() = getInfo("PWD")

    val cfTmpDir: Map<String, Any>
        get() = getInfo("TMPDIR")

    val cfUser: Map<String, Any>
        get() = getInfo("USER")

    fun getInfo(envVar: String): Map<String, Any> {
        val envString = getEnvVar(envVar)
        return if (envString != null) {
            Json.asMap(envString)
        } else Collections.emptyMap()

    }

    fun getEnvVar(varName: String): String? {
        var value: String?
        value = System.getenv(varName)

        if (value != null) {
            return value
        }

        value = System.getProperty(varName)

        return if (value != null) {
            value
        } else value

    }
}