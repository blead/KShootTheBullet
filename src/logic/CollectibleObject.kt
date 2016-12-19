package logic

import lib.AudioUtility

abstract class CollectibleObject(radius: Int, movingDuration: Int, z: Int, private val requiredGrabbingTime: Int) : TargetObject(radius, movingDuration, z) {
    private var grabbingTimeCount = 0

    fun ungrab() {
        grabbingTimeCount = 0
    }

    fun grab(player: PlayerStatus) {
        if (isDestroyed)
            return
        if (grabbingTimeCount >= requiredGrabbingTime) {
            AudioUtility.playSound("collect")
            collect(player)
            isDestroyed = true
            return
        }
        grabbingTimeCount++
    }

    abstract fun collect(player: PlayerStatus)
}
