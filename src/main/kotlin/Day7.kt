// Day 7: Handy Haversacks
class Day7 {

    private val ruleRegex = Regex("(.+) bags contain (.+)")
    private val bagContentRegex = Regex("(\\d+) (\\S+) (\\S+) bag|bags")

    fun getBagColorCount(
        input: List<String>,
        bagColor: String
    ): Int {
        val rules = toBagRules(input)
        return rules.keys.count { color -> hasBagColor(rules, bagColor, color) }
    }

    fun getBagColorTotalCount(
        input: List<String>,
        bagColor: String
    ): Int {
        val rules = toBagRules(input)
        return getBagColorCount(rules, bagColor)
    }

    private fun hasBagColor(
        rules: Map<String, BagRule>,
        bagColor: String,
        currentColor: String
    ): Boolean {
        val bagContent: List<String> = rules[currentColor]?.content?.map { it.color } ?: emptyList()
        return bagContent.contains(bagColor) || bagContent.any { color -> hasBagColor(rules, bagColor, color) }
    }

    private fun getBagColorCount(
        rules: Map<String, BagRule>,
        bagColor: String
    ): Int {
        return rules[bagColor]?.content?.let {
            content ->
            content.sumOf { it.quantity + it.quantity * getBagColorCount(rules, it.color) }
        } ?: 0
    }

    private fun toBagRules(
        input: List<String>
    ): Map<String, BagRule> {
        val bags = mutableMapOf<String, BagRule>()
        for (line in input) {
            val (bagColor, bagContent) = ruleRegex.find(line)!!.destructured
            val content = bagContentRegex.findAll(bagContent)
                .filter { it.destructured.component1().isNotEmpty() }
                .map {
                    val color = "${it.destructured.component2()} ${it.destructured.component3()}"
                    val quantity = it.destructured.component1().toInt()
                    Bag(color, quantity)
                }
                .toList()
            if (content.isNotEmpty()) {
                bags[bagColor] = BagRule(content)
            }
        }
        return bags
    }
}

private data class BagRule(
    val content: List<Bag>
)

private data class Bag(
    val color: String,
    val quantity: Int = 0
)
