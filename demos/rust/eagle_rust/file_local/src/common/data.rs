use serde_json::*;
pub fn data() -> Value{
    return json!({
        "hosts": "C:/Windows/System32/drivers/etc/hosts"
    })
}