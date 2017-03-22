package me.jessyan.rxerrorhandler.utils;

import android.support.annotation.Nullable;

/**
 * 预先处理   用于属性的检测
 *
 * @author wan7451
 * @data 2017/3/22
 */

public final class Preconditions {
    private Preconditions() {
    }

    /**
     * 检测值不能为false
     *
     * @param expression 被检测的值
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 检测值不能为false
     *
     * @param expression   被检测的值
     * @param errorMessage 错误提示，为Object的toString()
     */
    public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    /**
     * 检测值不能为false
     *
     * @param expression           被检测的值
     * @param errorMessageTemplate 错误提示，为Object的toString()
     * @param errorMessageArgs     错误提示数据
     */
    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 检测值不能为false
     *
     * @param expression 被检测的值
     */
    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * 检测值不能为false
     *
     * @param expression   被检测的值
     * @param errorMessage 错误提示，为Object的toString()
     */
    public static void checkState(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    /**
     * 检测值不能为false
     *
     * @param expression           被检测的值
     * @param errorMessageTemplate 错误提示，为Object的toString()
     * @param errorMessageArgs     错误提示数据
     */
    public static void checkState(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 检测不能为空
     *
     * @param reference 被检测的对象
     * @param <T>       检测对象的类型
     * @return 返回被检测的对象
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    /**
     * 检测不能为空
     *
     * @param reference    被检测的对象
     * @param <T>          检测对象的类型
     * @param errorMessage 错误提示
     * @return 返回被检测的对象
     */
    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }

    /**
     * 检测不能为空
     *
     * @param reference            被检测的对象
     * @param <T>                  检测对象的类型
     * @param errorMessageTemplate 错误提示
     * @param errorMessageArgs     错误提示
     * @return 返回被检测的对象
     */
    public static <T> T checkNotNull(T reference, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        if (reference == null) {
            throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
        } else {
            return reference;
        }
    }

    /**
     * 检测元素位置是否在集合中
     *
     * @param index 元素下标
     * @param size  集合大小
     * @return 元素下标
     */
    public static int checkElementIndex(int index, int size) {
        return checkElementIndex(index, size, "index");
    }

    /**
     * 检测元素位置是否在集合中
     *
     * @param index 元素下标
     * @param size  集合大小
     * @param desc  错误提示
     * @return 元素下标
     */
    public static int checkElementIndex(int index, int size, @Nullable String desc) {
        if (index >= 0 && index < size) {
            return index;
        } else {
            throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
        }
    }

    /**
     * 下标越界提示
     *
     * @param index 元素下标
     * @param size  集合长度
     * @param desc  错误描述
     * @return 错误提示
     */
    private static String badElementIndex(int index, int size, String desc) {
        if (index < 0) {
            return format("%s (%s) must not be negative", new Object[]{desc, Integer.valueOf(index)});
        } else if (size < 0) {
            throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(size).toString());
        } else {
            return format("%s (%s) must be less than size (%s)", new Object[]{desc, Integer.valueOf(index), Integer.valueOf(size)});
        }
    }

    /**
     * 检测元素位置是否在集合中
     *
     * @param index 元素下标
     * @param size  集合大小
     * @return 元素下标
     */
    public static int checkPositionIndex(int index, int size) {
        return checkPositionIndex(index, size, "index");
    }

    /**
     * 检测元素位置是否在集合中
     *
     * @param index 元素下标
     * @param size  集合大小
     * @param desc  错误提示
     * @return 元素下标
     */
    public static int checkPositionIndex(int index, int size, @Nullable String desc) {
        if (index >= 0 && index <= size) {
            return index;
        } else {
            throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
        }
    }


    /**
     * 下标越界提示
     *
     * @param index 元素下标
     * @param size  集合长度
     * @param desc  错误描述
     * @return 错误提示
     */
    private static String badPositionIndex(int index, int size, String desc) {
        if (index < 0) {
            return format("%s (%s) must not be negative", new Object[]{desc, Integer.valueOf(index)});
        } else if (size < 0) {
            throw new IllegalArgumentException((new StringBuilder(26)).append("negative size: ").append(size).toString());
        } else {
            return format("%s (%s) must not be greater than size (%s)", new Object[]{desc, Integer.valueOf(index), Integer.valueOf(size)});
        }
    }

    /**
     * 检测下标是否越界
     *
     * @param start 下标1
     * @param end   下标2
     * @param size  集合长度
     */
    public static void checkPositionIndexes(int start, int end, int size) {
        if (start < 0 || end < start || end > size) {
            throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
        }
    }

    /**
     * 下标错误提示
     *
     * @param start 下标1
     * @param end   下标2
     * @param size  集合长度
     * @return 错误提示
     */
    private static String badPositionIndexes(int start, int end, int size) {
        return start >= 0 && start <= size ? (end >= 0 && end <= size ? format("end index (%s) must not be less than start index (%s)", new Object[]{Integer.valueOf(end), Integer.valueOf(start)}) : badPositionIndex(end, size, "end index")) : badPositionIndex(start, size, "start index");
    }

    /**
     * 格式化要显示的内容
     *
     * @param template 显示的字符
     * @param args     显示的数据
     * @return 格式化好的内容
     */
    static String format(String template, @Nullable Object... args) {
        template = String.valueOf(template);
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;

        int i;
        int placeholderStart;
        for (i = 0; i < args.length; templateStart = placeholderStart + 2) {
            placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }

            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
        }

        builder.append(template.substring(templateStart));
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);

            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }

            builder.append(']');
        }

        return builder.toString();
    }
}
