package Ice.asundry.world.schematics;

import arc.struct.Seq;
import mindustry.game.Schematic;
import mindustry.game.Schematics;

public class IceSchematics {
    public final static Seq<Schematic> schematics = new Seq<>();

    static {

        Schematic[] sc1 = new Schematic[]{

                /**  空军捕杀器 */Schematics.readBase64("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"),

                /**  核心蓝图两潮涌 */Schematics.readBase64("bXNjaAF4nE2T65KbMAyFhbmYO2TZ1+CJOv3hJu6GHQoZQpLuC/Sld2ZTHZ/+aC7+kCwfS7KRV3mNJJvdDz9fxXz7/ir1fl636fZrfLh5lmybLpfZy8v/3nF225uXwz7tboHzuC53/7Fu0mz72/jmF7+5Xc3u4Xa/jf73vrkjHM3lNl894k+3aVf19aYBkr/fluM+rYsM691vp226+/Gyre8+rLI/3K5hH5Lflnl1J13RX9aHKi/ryf/Lpjyumx+vZ7edJLuqcTxL8nB3L5WbtvFnSOBDkrObZkmvbr6vYq/HoCwif/QvmYSPJXIMEZyRGLViPJRwGkYaWjGtmFYCyygsXIkUYmCFuVQnAmIiIVJEpoiEVTMTQ6mYSOm0RM5NdUGqKIkaSzJpWYFBEpZJWEpbzKEQA2euugYISeXIGpZGQr+UCKiImmiIlujYox7rCohhm5SW5g1YQlsH4YJWSVRELTFmgnRBzQKawo4BEWGImEjY/pSWxVCiNJxCqLakQKkb6a+UWkNthG519IdNKp4xehfDUtlaYXP7/Hx+PT8lfn5JpkzDiaYhj0p3qbBLE4IFlhbQKrTDnaJDBpUcoF6zjpqnXTPlmv2uoVIpGvWmptUnVehV7QVVRzo0vFuNLo+BlMgIS2iHkUKB9hs86aqDDi0RSm2ZR8t+trwhLcoGUkIb+aIoENSydW1oXRT1WlUbsgrOoNmx/A5F9vo9cB5GgomeF6JHulZhcUA90oVV0AoXoueF6HEhBk06kQjIiFwiowgnemBHB/ZzYD8HvoMD35CB7+6gOwTour9GA29I"),

                /**  太阳能电池 */Schematics.readBase64("bXNjaAF4nFVPWwrCMBCc7ta2Kn6IvUZOJH5ECFKIrTQpvbKXkDppieDkMbO7k4HggKZA5e3d+QC53kqcYhdt301PM1vvcfkrjbfjw+EcBgrzsr3LrWOYSCYOsxsBtDxQrCg32gEFl4BbWGkSK4TtbBfaJUlF/XuspLIR1MsH7fLWNSyhShc/kAI4po2zZsvdZ9KNmPQFsMAgIQ=="),

                /**  星辉核心 */Schematics.readBase64("bXNjaAF4nEVKWwrDIBAcNVqhgZ4kJyr9sIkBIbhhk1BydSlYjR+dYZmdByysgFnc2y8b5POl0bNfXeBhpRB3PEZiP8x0xMntgSLutZvduBOfsEdcyE2e0a/08TxsdPDoAfT4QzSRTVQNZA1loa6lEApdYfUwLUF5L+g2NVBXUWYGwt7yBej8RZcTkFOxCaKcxA8HoC1j")

        };

        schematics.add(sc1);
    }
}