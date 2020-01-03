package com.mango.zombies.helper;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class HiddenStringUtils {

    //region Constant Values
    public static final String ZOMBIES_SEQUENCE_HEADER = "" + ChatColor.RESET + ChatColor.UNDERLINE + ChatColor.RESET;
    public static final String ZOMBIES_SEQUENCE_FOOTER = "" + ChatColor.RESET + ChatColor.ITALIC + ChatColor.RESET;
    //endregion

    //region Public Static Methods
    /**
     * Encodes a string to be hidden in an ItemStack's lore.
     * @param hiddenString The string to hide.
     */
    public static String encodeString(String hiddenString) {
        return quote(stringToColors(hiddenString));
    }

    /**
     * Decodes a hidden string.
     * @param input The string to decode.
     */
    public static String extractHiddenString(String input) {
        return colorsToString(extract(input));
    }

    /**
     * Extracts a UUID from a hidden string assigned to an ItemStack's lore.
     */
    public static UUID extractUuidFromItemStack(ItemStack itemStack) {

        if (itemStack == null
                || itemStack.getItemMeta() == null
                || itemStack.getItemMeta().getLore() == null
                || itemStack.getItemMeta().getLore().size() < 1
                || itemStack.getItemMeta().getLore().get(0) == null)
            return null;

        try{
            return UUID.fromString(HiddenStringUtils.extractHiddenString(itemStack.getItemMeta().getLore().get(0)));
        } catch (Exception e) {
            return null;
        }
    }
    //endregion

    //region Private Methods
    private static char[] byteToHex(byte b) {
        int unsignedByte = (int) b - Byte.MIN_VALUE;
        return new char[]{unsignedIntToHex((unsignedByte >> 4) & 0xf), unsignedIntToHex(unsignedByte & 0xf)};
    }

    private static String colorsToString(String colors) {

        if (colors == null)
            return null;

        colors = colors.toLowerCase().replace("" + ChatColor.COLOR_CHAR, "");

        if (colors.length() % 2 != 0)
            colors = colors.substring(0, (colors.length() / 2) * 2);

        char[] chars = colors.toCharArray();
        byte[] bytes = new byte[chars.length / 2];

        for (int i = 0; i < chars.length; i += 2)
            bytes[i / 2] = hexToByte(chars[i], chars[i + 1]);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static String extract(String input) {

        if (input == null)
            return null;

        int start = input.indexOf(ZOMBIES_SEQUENCE_HEADER);
        int end = input.indexOf(ZOMBIES_SEQUENCE_FOOTER);

        if (start < 0 || end < 0)
            return null;

        return input.substring(start + ZOMBIES_SEQUENCE_HEADER.length(), end);
    }

    private static byte hexToByte(char hex1, char hex0) {
        return (byte) (((hexToUnsignedInt(hex1) << 4) | hexToUnsignedInt(hex0)) + Byte.MIN_VALUE);
    }

    private static int hexToUnsignedInt(char c) {

        if (c >= '0' && c <= '9') {
            return c - 48;
        } else if (c >= 'a' && c <= 'f') {
            return c - 87;
        } else {
            throw new IllegalArgumentException("Invalid hex char: out of range");
        }
    }

    private static String quote(String input) {

        if (input == null)
            return null;

        return ZOMBIES_SEQUENCE_HEADER + input + ZOMBIES_SEQUENCE_FOOTER;
    }

    private static String stringToColors(String normal) {

        if (normal == null)
            return null;

        byte[] bytes = normal.getBytes(StandardCharsets.UTF_8);
        char[] chars = new char[bytes.length * 4];

        for (int i = 0; i < bytes.length; i++) {

            char[] hex = byteToHex(bytes[i]);
            chars[i * 4] = ChatColor.COLOR_CHAR;
            chars[i * 4 + 1] = hex[0];
            chars[i * 4 + 2] = ChatColor.COLOR_CHAR;
            chars[i * 4 + 3] = hex[1];
        }

        return new String(chars);
    }

    private static char unsignedIntToHex(int i) {

        if (i >= 0 && i <= 9) {
            return (char) (i + 48);
        } else if (i >= 10 && i <= 15) {
            return (char) (i + 87);
        } else {
            throw new IllegalArgumentException("Invalid hex int: out of range");
        }
    }
    //endregion
}