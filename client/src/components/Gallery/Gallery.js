import { Image } from "../Image";
import "./Gallery.css";
export const Gallery = () => {
    return (
        <div id="portfolio" className="text-center">
            <div className="container">
                <div className="section-title">
                    <h2>Gallery</h2>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit
                        duis sed dapibus leonec.
                    </p>
                </div>
                <div className="row gx-5">
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="tyt125ul"
                            largeImage="./img/portfolio/01-large.jpg"
                            smallImage="./img/portfolio/01-small.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="tytu512l"
                            largeImage="./img/portfolio/01-large.jpg"
                            smallImage="./img/portfolio/01-small.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="tytul512"
                            largeImage="./img/portfolio/01-large.jpg"
                            smallImage="./img/portfolio/01-small.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="tytul123"
                            largeImage="./img/portfolio/01-large.jpg"
                            smallImage="./img/portfolio/01-small.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="tytul456"
                            largeImage="./img/portfolio/01-large.jpg"
                            smallImage="./img/portfolio/01-small.jpg"
                        />
                    </div>
                    <div className="col-sm-5 col-md-4 col-lg-3">
                        <Image
                            title="tytu152l"
                            largeImage="./img/portfolio/01-large.jpg"
                            smallImage="./img/portfolio/01-small.jpg"
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};
