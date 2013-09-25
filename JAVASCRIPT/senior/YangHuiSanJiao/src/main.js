/**
 * 杨辉三角形，要求输出第n行的最大值，例如第4行最大值是3
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * 1 5 10 10 5 1
 */
function cal(n) 
{	
	if(n <= 0)
	{
		return 0;
	}
	
	var max_num_of_the_current_line = 1;
	for(var i = 1; i < n/2; i++)
	{
		max_num_of_the_current_line = max_num_of_the_current_line * (n - i) / i;
	}
	
    return max_num_of_the_current_line;
}
