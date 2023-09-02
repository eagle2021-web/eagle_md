use rodio::Source;
use std::fs::File;
use std::io::BufReader;
use rodio::Decoder;
fn main() {
    // 打开音频文件
    let file = File::open("E:/CloudMusic/周杰伦-东风破 (Live).flac").expect("Failed to open audio file");
    let source = rodio::Decoder::new(BufReader::new(file)).unwrap();
    let duration = source.total_duration().unwrap();

    // 将时长转换为秒数
    let duration_secs = duration.as_secs();
    println!("Duration: {} seconds", duration_secs);
    // 打开音频设备
    let (_stream, stream_handle) = rodio::OutputStream::try_default().unwrap();
    let sink = rodio::Sink::try_new(&stream_handle).unwrap();

    // 添加音频到播放队列并播放
    sink.append(source);
    sink.play();

    // 等待播放完成
    std::thread::sleep(std::time::Duration::from_secs(duration_secs + 1));

    // 停止播放
    sink.stop();
}