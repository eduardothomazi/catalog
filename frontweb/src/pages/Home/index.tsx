import Navbar from "components/Navbar";
import './styles.css'
import { ReactComponent as MainImage} from 'assets/images/desk.svg'

const Home = () => {
    return(
        <>
            <Navbar />
            <div className="home-container">
                <div className="home-card">
                    <div className="home-content-container">
                        <h1>Known the best product catalog</h1>
                    </div>
                    <div className="home-image-container">
                        <MainImage/>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Home