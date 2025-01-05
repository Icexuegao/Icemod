package ice.ui.menus

import arc.flabel.FLabel
import arc.graphics.Color
import arc.scene.ui.layout.Table
import ice.ui.TableExtend.colorR
import ice.ui.TableExtend.icePaneG
import ice.ui.TableExtend.tableG
import ice.ui.TableExtend.tableGX
import ice.ui.tex.Colors
import ice.ui.tex.IceTex
import mindustry.gen.Icon

object AchievementDialog {
    fun set(table: Table) {
        rebuild(table)
    }

    private fun rebuild(table: Table) {
        table.icePaneG { t1 ->
            t1.tableGX { t ->
                t.tableG {
                    it.image(Icon.star).left().color(Colors.b5).fontScale(2f)
                    it.add(FLabel("@achievement")).fontScale(2f)
                }
            }.height(90f).row()
            t1.tableG { k ->
                var i = 0
                IceAchievement.entries.forEach {
                    i++
                    k.tableGX(IceTex.background) { b ->
                        b.add(FLabel(it.name).colorR(it.color)).padTop(5f).row()
                        b.add(it.description).grow().wrap()
                    }.height(140f).pad(5f).margin(MenusDialog.backMargin + 2)
                    if (i % 3 == 0) k.row()
                }
            }
        }
    }

    enum class IceAchievement(val description: String, var color: Color, var lock: Boolean) {
        灾祸("一直都无法避免,不是吗", Colors.r1, true),
        医学博士("外星医生!!!", Colors.r1, true),
        血田("别担心,洗礼很快就会结束", Colors.r1, true),
        传教者("异端末路", Colors.y1, true),
        空间风暴I("你感觉到一阵嗡嗡,声音越来越大,你的头开始一抽一跳的痛,有东西要来了", Colors.y1, true),
        空间风暴II("现实正在破碎!", Colors.y1, true),
        空间风暴III("时空让人感觉很沉重,仿佛这一切还没有结束", Colors.y1, true)
    }
}