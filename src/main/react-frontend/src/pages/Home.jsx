import React from 'react'
import { Link } from 'react-router-dom'

function Home() {
  return (
    <div className='container text-center pt-5'>
      <Link to={"/kitchen"} className="btn btn-primary btn-lg m-5">
        Cuisine
      </Link>
      <Link to={"/salle"} className="btn btn-primary btn-lg">
        Service
      </Link>
    </div>
  )
}

export default Home