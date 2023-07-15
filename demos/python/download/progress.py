import time

from progressbar import ProgressBar, Percentage, Bar, Timer, ETA, FileTransferSpeed

if __name__ == '__main__':
    widgets = ['Progress: ', Percentage(), ' ', Bar('#'), ' ', Timer(), ' ', ETA(), ' ', FileTransferSpeed()]
    progress = ProgressBar(widgets=widgets)
    for i in progress(range(100)):
        # do something
        time.sleep(0.05)
