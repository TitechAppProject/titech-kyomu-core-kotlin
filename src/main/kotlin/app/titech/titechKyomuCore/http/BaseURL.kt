package app.titech.titechKyomuCore.http

class BaseURL {
    companion object {
        var host = "kyomu0.gakumu.titech.ac.jp"
        var origin = "https://kyomu0.gakumu.titech.ac.jp"

        fun changeToMock() {
            host = "kyomu-mock.isct.app"
            origin = "https://kyomu-mock.isct.app"
        }
    }
}