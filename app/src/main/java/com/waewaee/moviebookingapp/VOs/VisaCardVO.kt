package alirezat775.carouselview.sample

import alirezat775.lib.carouselview.CarouselModel

class VisaCardVO constructor(private var id: Int) : CarouselModel() {

    fun getId(): Int {
        return id
    }
}
