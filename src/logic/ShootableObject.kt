package logic

abstract class ShootableObject(radius: Int, movingDuration: Int, z: Int, protected var reward: Int) : TargetObject(radius, movingDuration, z) {
    var life = 0
        set(value) {
            field = if(value<0) 0 else value
            isDestroyed = field == 0
        }

    open fun hit(player: PlayerStatus) {
        life -= player.currentGun.attack
        if (isDestroyed)
            player.increaseScore(reward)
    }
}
