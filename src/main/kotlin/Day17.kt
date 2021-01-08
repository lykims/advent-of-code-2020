// Day 17: Conway Cubes
class Day17 {

    fun getActiveCubeCount(
        z: Int,
        input: List<String>,
        nCycles: Int
    ): Int {
        var cubes = Cubes.build(input, z)
        repeat(nCycles) { cubes = cubes.cycle() }
        return cubes.activeCubes.size
    }

    fun getActiveHyperCubeCount(
        z: Int,
        w: Int,
        input: List<String>,
        nCycles: Int
    ): Int {
        var cubes = Cubes.build(input, z, w)
        repeat(nCycles) { cubes = cubes.cycle() }
        return cubes.activeCubes.size
    }
}

private data class Cubes(
    val activeCubes: MutableList<Cube>
) {
    fun cycle(): Cubes {
        val newActiveCubes = mutableListOf<Cube>()
        val range = getRange()
        for (x in range) {
            for (y in range) {
                for (z in range) {
                    for (w in getHyperRange(range)) {
                        val cube = Cube.build(x, y, z, if (areHyperCubes()) w else null)
                        val count = getActiveNeighbourCount(cube)
                        if (cube.becomesAlive(activeCubes, count) || cube.staysAlive(activeCubes, count)) {
                            newActiveCubes.add(cube)
                        }
                    }
                }
            }
        }
        return Cubes(newActiveCubes)
    }

    private fun getRange(): IntRange {
        val max = activeCubes.map(Cube::flatten).flatten().maxOrNull() ?: 0
        return IntRange(-max - 1, max + 1)
    }

    private fun getActiveNeighbourCount(
        cube: Cube
    ): Int {
        var count = 0
        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    for (w in getHyperRange(-1..1)) {
                        if (!(x == 0 && y == 0 && z == 0 && w == 0)) {
                            val neighbourCube = Cube.build(
                                x = cube.x + x,
                                y = cube.y + y,
                                z = cube.z + z,
                                w = if (cube is Cube4d) cube.w + w else null
                            )
                            if (neighbourCube.isAlive(activeCubes)) {
                                count++
                            }
                        }
                    }
                }
            }
        }
        return count
    }

    private fun getHyperRange(range: IntRange): IntRange {
        return if (areHyperCubes()) range else 0..0
    }

    private fun areHyperCubes(): Boolean = activeCubes.size > 0 && activeCubes[0] is Cube4d

    companion object {
        fun build(
            input: List<String>,
            z: Int,
            w: Int? = null
        ): Cubes {
            val cubes = mutableListOf<Cube>()
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    if (cell == '#') {
                        val cube = Cube.build(x, y, z, w)
                        cubes.add(cube)
                    }
                }
            }
            return Cubes(cubes)
        }
    }
}

private abstract class Cube(
    open val x: Int,
    open val y: Int,
    open val z: Int
) {
    abstract fun flatten(): List<Int>

    fun isAlive(activeCubes: List<Cube>) = activeCubes.contains(this)

    fun becomesAlive(activeCubes: List<Cube>, count: Int) = !isAlive(activeCubes) && count == 3

    fun staysAlive(activeCubes: List<Cube>, count: Int) = isAlive(activeCubes) && (count == 2 || count == 3)

    companion object {
        fun build(
            x: Int,
            y: Int,
            z: Int,
            w: Int? = null
        ) = if (w == null) Cube3d(x, y, z) else Cube4d(x, y, z, w)
    }
}

private data class Cube3d(
    override val x: Int,
    override val y: Int,
    override val z: Int
) : Cube(x, y, z) {
    override fun flatten(): List<Int> = listOf(x, y, z)
}

private data class Cube4d(
    override val x: Int,
    override val y: Int,
    override val z: Int,
    val w: Int
) : Cube(x, y, z) {
    override fun flatten(): List<Int> = listOf(x, y, z, w)
}