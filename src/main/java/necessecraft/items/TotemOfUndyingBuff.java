package necessecraft.items;

import necesse.engine.localization.Localization;
import necesse.engine.network.packet.PacketLifelineEvent;
import necesse.engine.registries.BuffRegistry;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.MobHitEvent;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.LifelineBuff;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.TrinketBuff;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.level.maps.Level;

public class TotemOfUndyingBuff extends TrinketBuff {
    public TotemOfUndyingBuff() {
    }

    public void init(ActiveBuff buff) {
    }

    public ListGameTooltips getTrinketTooltip() {
        ListGameTooltips tooltips = super.getTrinketTooltip();
        tooltips.add(Localization.translate("itemtooltip", "totemofundyingtip"));
        return tooltips;
    }

    public void onHit(ActiveBuff buff, MobHitEvent hitEvent) {
        super.onHit(buff, hitEvent);
        Level level = buff.owner.getLevel();
        if (level.isServerLevel() && !buff.owner.buffManager.hasBuff(BuffRegistry.Debuffs.LIFELINE_COOLDOWN.getID()) && hitEvent.getExpectedHealth() <= 0) {
            hitEvent.prevent();
            buff.owner.setHealth(Math.max(10, buff.owner.getMaxHealth() / 2));
            buff.owner.buffManager.addBuff(new ActiveBuff(BuffRegistry.Potions.COMBAT_REGEN, buff.owner, 60f, (Attacker)null), true);
            level.getServer().network.sendToClientsAt(new PacketLifelineEvent(buff.owner.getUniqueID()), level);
        }

    }
}
