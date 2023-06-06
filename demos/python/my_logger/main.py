# This is a sample Python script.
import logging
import os

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
log_dir = 'log'  # 日志文件目录
if not os.path.exists(log_dir):  # 如果目录不存在，则创建目录
    os.makedirs(log_dir)
formatter = logging.Formatter('%(asctime)s %(name)s - %(filename)s:%(lineno)d - [%(levelname)s]  %(message)s')
log_file = os.path.join(log_dir, 'root_logger.log')

logger_console = logging.getLogger('logger_console')
logger_console.setLevel(logging.DEBUG)
console_handler = logging.StreamHandler()
logger_console.addHandler(console_handler)

file_handler = logging.FileHandler(
    filename=log_file,
    mode='a',  # 追加写入
    encoding='utf-8',
    delay=False,
)

# file_handler.setFormatter(formatter)
logger = logging.getLogger('root_logger')
logger.addHandler(file_handler)


def setup_logging():
    for handler in [file_handler, console_handler]:
        handler.setFormatter(formatter)


setup_logging()
if __name__ == '__main__':
    print(logger.name)
    logger.info('这是一条有格式的 INFO 日志')
    logger.warning('这是一条警告日志')
    logger.error('这是一条错误日志')
    logger_console.warning('this is a warning message from logger_console')
    logger_console.error('this is an error message from logger_console')
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
