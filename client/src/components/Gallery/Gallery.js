import { Image } from "../Image";
import "./Gallery.css";
export const Gallery = () => {
    return (
        <div id="portfolio" className="text-center">
            <div className="container">
                <div className="section-title">
                    <h2>Gallery</h2>
                    <p>Modern facility, top-in-the-industry technology.</p>
                </div>
                <div className="row gx-5">
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="Modern building"
                            largeImage="./img/portfolio/a.jpg"
                            smallImage="./img/portfolio/a.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="Our patients"
                            largeImage="./img/portfolio/wheelchair.jpg"
                            smallImage="./img/portfolio/wheelchair.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="Beatiful interior"
                            largeImage="./img/portfolio/corridor.jpg"
                            smallImage="./img/portfolio/corridor.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="Best technologies"
                            largeImage="./img/portfolio/rtg.jpg"
                            smallImage="./img/portfolio/rtg.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="Kind service"
                            largeImage="./img/portfolio/reception.png"
                            smallImage="./img/portfolio/reception.png"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="Best specialists"
                            largeImage="./img/portfolio/operation.jpg"
                            smallImage="./img/portfolio/operation.jpg"
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};
