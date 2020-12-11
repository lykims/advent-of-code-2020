// Day 4: Passport Processing
class Day4 {

    private val fieldRegex = Regex("(\\S+):(\\S+)")

    fun getNumberOfValidPassports(
        input: List<String>
    ): Int {
        val passports = toPassports(input)
        return passports.count { it.hasValidFields() }
    }

    fun getNumberOfValidPassports2(
        input: List<String>
    ): Int {
        val passports = toPassports(input)
        return passports.count { it.hasValidValues() }
    }

    private fun toPassports(input: List<String>): List<Passport> {
        val passports = mutableListOf<Passport>()
        var passportLine = ""
        input.forEachIndexed { index, line ->
            if (line.trim().isEmpty()) {
                if (passportLine.isNotEmpty()) {
                    passports.add(toPassport(passportLine))
                    passportLine = ""
                }
            } else {
                passportLine += "$line "
                if (index == (input.size - 1)) {
                    passports.add(toPassport(passportLine))
                }
            }
        }
        return passports
    }

    private fun toPassport(passportLine: String): Passport {
        val passportFields = passportLine.trim().split(" ")
            .map { field -> fieldRegex.find(field)!!.destructured }
            .map { (key, value) -> key to value }
            .toMap()
        return Passport(passportFields)
    }
}

private class Passport(
    val fields: Map<String, String>
) {
    fun hasValidFields(): Boolean {
        return fields.keys.containsAll(FIELD_POLICIES.keys)
    }

    fun hasValidValues(): Boolean {
        return hasValidFields()
                && FIELD_POLICIES.all { (field, policy) -> fields[field]?.let { policy.matches(it) } == true }
    }

    companion object {
        val FIELD_POLICIES = mapOf(
            "byr" to Regex("19[2-9]\\d|200[0-2]"),
            "iyr" to Regex("201\\d|2020"),
            "eyr" to Regex("202\\d|2030"),
            "hgt" to Regex("((15\\d|1[6-8]\\d|19[0-3])cm)|((59|6\\d|7[0-6])in)"),
            "hcl" to Regex("#[\\da-f]{6}"),
            "ecl" to Regex("amb|blu|brn|gry|grn|hzl|oth"),
            "pid" to Regex("\\d{9}")
        )
    }
}