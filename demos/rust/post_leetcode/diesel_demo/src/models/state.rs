use std::sync::Mutex;

pub struct AppState {
    pub health_check_response: String,
    pub visit_count: Mutex<u32>
}

pub struct AppState2 {
    pub health_check_response: String,
    pub visit_count: Mutex<u8>
}