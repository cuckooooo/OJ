package huawei;

/**
 * <pre>
 * ���ѹ�����
 * 
 * </pre>
 */
public final class HoneycombUtils
{
    
    /**
     * <Ĭ�Ϲ��캯��>
     */
    private HoneycombUtils()
    {
    }
    
    /**
     * <pre>
     * ��ȡ����һȦ�е������ʵ���(Ȧ��>=2)
     * 
     * @param cycleNo Ȧ��
     * @return
     * </pre>
     */
    public static int getMaxOfRealNumInCycle(int cycleNo)
    {
        return getMinOfRealNumInCycle(cycleNo) + getMaxColumnNumInCycle(cycleNo) - 1;
    }
    
    /**
     * <pre>
     * ��ȡ����һȦ�е���С��ʵ���
     * 
     * @param cycleNo Ȧ��
     * @return
     * </pre>
     */
    public static int getMinOfRealNumInCycle(int cycleNo)
    {
        return 2 + 3 * (cycleNo - 1) * (cycleNo - 2);
    }
    
    /**
     * <pre>
     * ��ȡһȦ�е�����к�(Ȧ��>=2)
     * 
     * @param cycleNo Ȧ��
     * @return
     * </pre>
     */
    public static int getMaxColumnNumInCycle(int cycleNo)
    {
        return 6 * (cycleNo - 1);
    }
}