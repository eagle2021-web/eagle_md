class Partition:
    @classmethod
    def get_div_list(cls, total_size: int, max_thread: int = 3):
        print(f'total_size = {total_size}')
        rest_size = total_size - 0
        # 100M一个
        thread_num = int(rest_size / (100 * 1024)) + 1
        if thread_num > max_thread:
            thread_num = max_thread
        step = rest_size // thread_num
        divided_list = []
        init_size = 0
        for i in range(thread_num):
            s_pos = init_size + step * i
            e_pos = init_size + step * (i + 1) - 1
            divided_list.append([s_pos, e_pos])
        divided_list[-1][1] = total_size - 1
        print(divided_list)
        return divided_list
