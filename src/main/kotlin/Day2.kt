// Day 2: Password Philosophy
class Day2 {

    private val passwordInputRegex = Regex("(\\d+)-(\\d+) (\\S): (.+)")

    fun getNumberOfValidPasswords(
        input: List<String>
    ): Int {
        var numberOfValidPasswords = 0
        for (passwordInput in input) {
            val entry = toPasswordEntry1(passwordInput)
            val passwordPolicy = entry.policy as PasswordPolicy1
            val characterCount = entry.password.count { c -> c == passwordPolicy.character }
            if (characterCount in passwordPolicy.min..passwordPolicy.max) {
                numberOfValidPasswords++
            }
        }
        return numberOfValidPasswords
    }

    fun getNumberOfValidPasswords2(
        input: List<String>
    ): Int {
        var numberOfValidPasswords = 0
        for (passwordInput in input) {
            val entry = toPasswordEntry2(passwordInput)
            val passwordPolicy = entry.policy as PasswordPolicy2
            if (passwordPolicy.secondPosition < entry.password.length &&
                hasOneCharacterOccurrence(passwordPolicy, entry.password)
            ) {
                numberOfValidPasswords++
            }
        }
        return numberOfValidPasswords
    }

    private fun toPasswordEntry1(passwordInput: String): PasswordEntry {
        val (min, max, character, password) = passwordInputRegex.find(passwordInput)!!.destructured
        val passwordPolicy = PasswordPolicy1(
            min = min.toInt(),
            max = max.toInt(),
            character = character[0]
        )
        return PasswordEntry(
            policy = passwordPolicy,
            password = password
        )
    }

    private fun toPasswordEntry2(passwordInput: String): PasswordEntry {
        val (firstPosition, secondPosition, character, password) = passwordInputRegex.find(passwordInput)!!.destructured
        val passwordPolicy = PasswordPolicy2(
            firstPosition = firstPosition.toInt() - 1,
            secondPosition = secondPosition.toInt() - 1,
            character = character[0]
        )
        return PasswordEntry(
            policy = passwordPolicy,
            password = password
        )
    }

    private fun hasOneCharacterOccurrence(
        passwordPolicy: PasswordPolicy2,
        password: String
    ): Boolean {
        val firstPositionCharacter = password[passwordPolicy.firstPosition]
        val secondPositionCharacter = password[passwordPolicy.secondPosition]
        return (firstPositionCharacter == passwordPolicy.character) != (secondPositionCharacter == passwordPolicy.character)
    }
}

private data class PasswordEntry(
    val policy: PasswordPolicy,
    val password: String
)

private data class PasswordPolicy1(
    override val character: Char,
    val min: Int,
    val max: Int
) : PasswordPolicy

private data class PasswordPolicy2(
    override val character: Char,
    val firstPosition: Int,
    val secondPosition: Int
) : PasswordPolicy

private interface PasswordPolicy {
    val character: Char
}
