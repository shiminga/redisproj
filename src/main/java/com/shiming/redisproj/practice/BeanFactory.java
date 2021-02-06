package com.shiming.redisproj.practice;

public interface BeanFactory {
//    /**
//     *1.factoryBean的 bean name前面加&，表示返回的是factory本身，而非factorybean的getObject返回的实例
//     */
//    String FACTORY_BEAN_PREFIX = "&";
//
//    Object getBean(String var1) throws BeansException;
//
//    <T> T getBean(String var1, Class<T> var2) throws BeansException;
//
//    Object getBean(String var1, Object... var2) throws BeansException;
//
//    <T> T getBean(Class<T> var1) throws BeansException;
//
//    <T> T getBean(Class<T> var1, Object... var2) throws BeansException;
//
//    <T> ObjectProvider<T> getBeanProvider(Class<T> var1);
//
//    <T> ObjectProvider<T> getBeanProvider(ResolvableType var1);
//
//    boolean containsBean(String var1);
//
//    boolean isSingleton(String var1) throws NoSuchBeanDefinitionException;
//
//    boolean isPrototype(String var1) throws NoSuchBeanDefinitionException;
//
//    boolean isTypeMatch(String var1, ResolvableType var2) throws NoSuchBeanDefinitionException;
//
//    boolean isTypeMatch(String var1, Class<?> var2) throws NoSuchBeanDefinitionException;
//
//    @Nullable
//    Class<?> getType(String var1) throws NoSuchBeanDefinitionException;
//
//    @Nullable
//    Class<?> getType(String var1, boolean var2) throws NoSuchBeanDefinitionException;

    String[] getAliases(String var1);
}
