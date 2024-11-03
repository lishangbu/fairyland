package io.github.lishangbu.fairyland.iogame.util;

import io.github.lishangbu.fairyland.iogame.exception.IllegalArgumentMsgException;
import io.github.lishangbu.fairyland.iogame.exception.IllegalStateMsgException;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 断言工具类，用于验证参数.
 *
 * <p>在运行时早期和清晰地识别程序员错误非常有用。</p>
 *
 * <p>例如，如果公共方法的方法声明不允许 {@code null} 参数，
 * 则可以使用 {@code Assert} 来验证该参数。这样做清楚地指示了参数
 * 违反的情况，并保护类的不变性。</p>
 *
 *
 * <p>通常用于验证方法参数，而不是配置属性，以检查通常是程序员错误
 * 而不是配置错误的情况。与配置初始化代码相比，在此类方法中通常
 * 没有必要回退到默认值。</p>
 *
 * <p>该类类似于 JUnit 的断言库。如果参数值被认为无效，则会抛出
 * {@link IllegalArgumentMsgException}（通常）。</p>
 * 例如：
 *
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
 *
 * <p>主要供框架内部使用；如需更全面的断言工具集合，可以考虑
 * {@code org.apache.commons.lang3.Validate} 来自
 * <a href="https://commons.apache.org/proper/commons-lang/">Apache Commons Lang</a>，
 * Google Guava 的
 * <a href="https://github.com/google/guava/wiki/PreconditionsExplained">Preconditions</a>，
 * 或类似的第三方库。</p>
 *
 * @author lishangbu
 */
public abstract class Assert {

    /**
     * 断言一个布尔表达式，如果表达式的结果为 {@code false}，则抛出 {@code IllegalStateMsgException}。
     * <p>如果希望在断言失败时抛出 {@code IllegalArgumentMsgException}，请调用 {@link #isTrue}。
     * <pre class="code">Assert.state(id == null, "id 属性必须尚未初始化");</pre>
     *
     * @param expression 一个布尔表达式
     * @param message    如果断言失败使用的异常消息
     * @throws IllegalStateMsgException 如果 {@code expression} 为 {@code false}
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateMsgException(message);
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式的结果为 {@code false}，则抛出 {@code IllegalStateMsgException}。
     * <p>如果您希望在断言失败时抛出 {@code IllegalArgumentMsgException}，请调用 {@link #isTrue}。
     * <pre class="code">
     * Assert.state(entity.getId() == null,
     *     () -&gt; "ID for entity " + entity.getName() + " must not already be initialized");
     * </pre>
     *
     * @param expression      一个布尔表达式
     * @param messageSupplier 断言失败时使用的异常消息供应者
     * @throws IllegalStateMsgException 如果 {@code expression} 为 {@code false}
     */
    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalStateMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式的结果为 {@code false}，则抛出 {@code IllegalArgumentMsgException}。
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression 一个布尔表达式
     * @param message    如果断言失败使用的异常消息
     * @throws IllegalArgumentMsgException 如果 {@code expression} 为 {@code false}
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert a boolean expression, throwing an {@code IllegalArgumentMsgException}
     * if the expression evaluates to {@code false}.
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, () -&gt; "The value '" + i + "' must be greater than zero");
     * </pre>
     *
     * @param expression      a boolean expression
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if {@code expression} is {@code false}
     * @since 5.0
     */
    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式的结果为 {@code true}，则抛出 {@code IllegalArgumentMsgException}。
     * <pre class="code">Assert.isFalse(i &lt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression 一个布尔表达式
     * @param message    如果断言失败使用的异常消息
     * @throws IllegalArgumentMsgException 如果 {@code expression} 为 {@code false}
     */
    public static void isFalse(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentMsgException(message);
        }
    }


    /**
     * Assert that an object is {@code null}.
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the object is not {@code null}
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that an object is {@code null}.
     * <pre class="code">
     * Assert.isNull(value, () -&gt; "The value '" + value + "' must be null");
     * </pre>
     *
     * @param object          the object to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the object is not {@code null}
     * @since 5.0
     */
    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the object is {@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">
     * Assert.notNull(entity.getId(),
     *     () -&gt; "ID for entity " + entity.getName() + " must not be null");
     * </pre>
     *
     * @param object          the object to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the object is {@code null}
     * @since 5.0
     */
    public static void notNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be {@code null} and not the empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the text is empty
     */
    public static void hasLength(String text, String message) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be {@code null} and not the empty String.
     * <pre class="code">
     * Assert.hasLength(account.getName(),
     *     () -&gt; "Name for account '" + account.getId() + "' must not be empty");
     * </pre>
     *
     * @param text            the String to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the text is empty
     * @since 5.0
     */
    public static void hasLength(String text, Supplier<String> messageSupplier) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be {@code null} and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the text does not contain valid text content
     */
    public static void hasText(String text, String message) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be {@code null} and must contain at least one non-whitespace character.
     * <pre class="code">
     * Assert.hasText(account.getName(),
     *     () -&gt; "Name for account '" + account.getId() + "' must not be empty");
     * </pre>
     *
     * @param text            the String to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the text does not contain valid text content
     * @since 5.0
     */
    public static void hasText(String text, Supplier<String> messageSupplier) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     * @param message      the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the text contains the substring
     */
    public static void doesNotContain(String textToSearch, String substring, String message) {
        if ((textToSearch != null && !textToSearch.isEmpty()) && (substring != null && !substring.isEmpty()) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">
     * Assert.doesNotContain(name, forbidden, () -&gt; "Name must not contain '" + forbidden + "'");
     * </pre>
     *
     * @param textToSearch    the text to search
     * @param substring       the substring to find within the text
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the text contains the substring
     * @since 5.0
     */
    public static void doesNotContain(String textToSearch, String substring, Supplier<String> messageSupplier) {
        if ((textToSearch != null && !textToSearch.isEmpty()) && (substring != null && !substring.isEmpty()) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the object array is {@code null} or contains no elements
     */
    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">
     * Assert.notEmpty(array, () -&gt; "The " + arrayType + " array must contain elements");
     * </pre>
     *
     * @param array           the array to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the object array is {@code null} or contains no elements
     * @since 5.0
     */
    public static void notEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that an array contains no {@code null} elements.
     * <p>Note: Does not complain if the array is empty!
     * <pre class="code">Assert.noNullElements(array, "The array must contain non-null elements");</pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentMsgException(message);
                }
            }
        }
    }

    /**
     * Assert that an array contains no {@code null} elements.
     * <p>Note: Does not complain if the array is empty!
     * <pre class="code">
     * Assert.noNullElements(array, () -&gt; "The " + arrayType + " array must contain non-null elements");
     * </pre>
     *
     * @param array           the array to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the object array contains a {@code null} element
     * @since 5.0
     */
    public static void noNullElements(Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
     *
     * @param collection the collection to check
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the collection is {@code null} or
     *                                     contains no elements
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">
     * Assert.notEmpty(collection, () -&gt; "The " + collectionType + " collection must contain elements");
     * </pre>
     *
     * @param collection      the collection to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the collection is {@code null} or
     *                                     contains no elements
     * @since 5.0
     */
    public static void notEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that a collection contains no {@code null} elements.
     * <p>Note: Does not complain if the collection is empty!
     * <pre class="code">Assert.noNullElements(collection, "Collection must contain non-null elements");</pre>
     *
     * @param collection the collection to check
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the collection contains a {@code null} element
     * @since 5.2
     */
    public static void noNullElements(Collection<?> collection, String message) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentMsgException(message);
                }
            }
        }
    }

    /**
     * Assert that a collection contains no {@code null} elements.
     * <p>Note: Does not complain if the collection is empty!
     * <pre class="code">
     * Assert.noNullElements(collection, () -&gt; "Collection " + collectionName + " must contain non-null elements");
     * </pre>
     *
     * @param collection      the collection to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the collection contains a {@code null} element
     * @since 5.2
     */
    public static void noNullElements(Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     *
     * @param map     the map to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentMsgException if the map is {@code null} or contains no entries
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentMsgException(message);
        }
    }

    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">
     * Assert.notEmpty(map, () -&gt; "The " + mapType + " map must contain entries");
     * </pre>
     *
     * @param map             the map to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails
     * @throws IllegalArgumentMsgException if the map is {@code null} or contains no entries
     * @since 5.0
     */
    public static void notEmpty(Map<?, ?> map, Supplier<String> messageSupplier) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentMsgException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo, "Foo expected");</pre>
     *
     * @param type    the type to check against
     * @param obj     the object to check
     * @param message a message which will be prepended to provide further context.
     *                If it is empty or ends in ":" or ";" or "," or ".", a full exception message
     *                will be appended. If it ends in a space, the name of the offending object's
     *                type will be appended. In any other case, a ":" with a space and the name
     *                of the offending object's type will be appended.
     * @throws IllegalArgumentMsgException if the object is not an instance of type
     */
    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, message);
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">
     * Assert.instanceOf(Foo.class, foo, () -&gt; "Processing " + Foo.class.getSimpleName() + ":");
     * </pre>
     *
     * @param type            the type to check against
     * @param obj             the object to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails. See {@link #isInstanceOf(Class, Object, String)} for details.
     * @throws IllegalArgumentMsgException if the object is not an instance of type
     * @since 5.0
     */
    public static void isInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
     *
     * @param type the type to check against
     * @param obj  the object to check
     * @throws IllegalArgumentMsgException if the object is not an instance of type
     */
    public static void isInstanceOf(Class<?> type, Object obj) {
        isInstanceOf(type, obj, "");
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * <pre class="code">Assert.isAssignable(Number.class, myClass, "Number expected");</pre>
     *
     * @param superType the supertype to check against
     * @param subType   the subtype to check
     * @param message   a message which will be prepended to provide further context.
     *                  If it is empty or ends in ":" or ";" or "," or ".", a full exception message
     *                  will be appended. If it ends in a space, the name of the offending subtype
     *                  will be appended. In any other case, a ":" with a space and the name of the
     *                  offending subtype will be appended.
     * @throws IllegalArgumentMsgException if the classes are not assignable
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Supertype to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, message);
        }
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * <pre class="code">
     * Assert.isAssignable(Number.class, myClass, () -&gt; "Processing " + myAttributeName + ":");
     * </pre>
     *
     * @param superType       the supertype to check against
     * @param subType         the subtype to check
     * @param messageSupplier a supplier for the exception message to use if the
     *                        assertion fails. See {@link #isAssignable(Class, Class, String)} for details.
     * @throws IllegalArgumentMsgException if the classes are not assignable
     * @since 5.0
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, Supplier<String> messageSupplier) {
        notNull(superType, "Supertype to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, nullSafeGet(messageSupplier));
        }
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType the supertype to check
     * @param subType   the subtype to check
     * @throws IllegalArgumentMsgException if the classes are not assignable
     */
    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }


    private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
        String className = (obj != null ? obj.getClass().getName() : "null");
        String result = "";
        boolean defaultMessage = true;
        if (msg != null && !msg.isEmpty()) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + ("Object of class [" + className + "] must be an instance of " + type);
        }
        throw new IllegalArgumentMsgException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (msg != null && !msg.isEmpty()) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + (subType + " is not assignable to " + superType);
        }
        throw new IllegalArgumentMsgException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }


    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }

}
