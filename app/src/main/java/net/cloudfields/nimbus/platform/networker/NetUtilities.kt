package net.cloudfields.nimbus.platform.networker

class NetUtilities {
    companion object {

        fun Identifier(): String {
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
            return (1..16)
                .map { allowedChars.random() }
                .joinToString("")
        }

        fun HTTPStatusValidation(status: Int): Boolean {
            when (status) {
                //accepted cases
                in 100..103 -> return true
                in 200..203 -> return true
                in 300..308 -> return true

                //rejected cases
                in 400..451 -> return false
                in 500..511 -> return false

                //dubious cases to be based on service rules
                in 204..208 -> return false

                //anything not above: reject
                else -> return false
            }
        }
    }
}