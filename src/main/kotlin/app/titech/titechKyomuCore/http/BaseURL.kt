package app.titech.titechKyomuCore.http

class BaseURL {
    companion object {
        var host = "kyomu0.gakumu.titech.ac.jp"
        var origin = "https://kyomu0.gakumu.titech.ac.jp"

        fun changeToMock() {
            host = "titech-kyomu-mock.s3.ap-northeast-1.amazonaws.com"
            origin = "https://titech-kyomu-mock.s3.ap-northeast-1.amazonaws.com"
        }
    }
}