package huawei;

/**
 * <pre>
 * 蜂窝工具类
 * 
 * </pre>
 */
public final class HoneycombUtils
{
    
    /**
     * <默认构造函数>
     */
    private HoneycombUtils()
    {
    }
    
    /**
     * <pre>
     * 获取蜂窝一圈中的最大真实编号(圈号>=2)
     * 
     * @param cycleNo 圈号
     * @return
     * </pre>
     */
    public static int getMaxOfRealNumInCycle(int cycleNo)
    {
        return getMinOfRealNumInCycle(cycleNo) + getMaxColumnNumInCycle(cycleNo) - 1;
    }
    
    /**
     * <pre>
     * 获取蜂窝一圈中的最小真实编号
     * 
     * @param cycleNo 圈号
     * @return
     * </pre>
     */
    public static int getMinOfRealNumInCycle(int cycleNo)
    {
        return 2 + 3 * (cycleNo - 1) * (cycleNo - 2);
    }
    
    /**
     * <pre>
     * 获取一圈中的最大列号(圈号>=2)
     * 
     * @param cycleNo 圈号
     * @return
     * </pre>
     */
    public static int getMaxColumnNumInCycle(int cycleNo)
    {
        return 6 * (cycleNo - 1);
    }
}